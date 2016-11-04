package ua.in.dris4ecoder.controllers.businessControllers;

import javafx.stage.Stage;
import ua.in.dris4ecoder.model.businessObjects.*;
import ua.in.dris4ecoder.model.dao.RestaurantDao;
import ua.in.dris4ecoder.view.customControls.WarningsDialogWindow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Korneyko on 30.08.2016 17:24.
 */
public class ManagementController implements BusinessController {

    private RestaurantDao<Contractor> contractorRestaurantDao;
    private RestaurantDao<PurchaseInvoice> purchaseInvoiceRestaurantDao;
    private RestaurantDao<SalesInvoice> salesInvoiceRestaurantDao;
    private RestaurantDao<WarehousePosition> warehousePositionRestaurantDao;

    //--------------------------------------Contractor------------------------------------------------
    public void addContractor(Contractor contractor) {
        contractorRestaurantDao.addItem(contractor);
    }

    public void addContractor(String contractorName) {
        addContractor(new Contractor(contractorName));
    }

    public Contractor findContractor(String contractorName) {
        return contractorRestaurantDao.findItem(contractorName);
    }

    public List<Contractor> findAllContractors() {
        return contractorRestaurantDao.findAll();
    }

    public void editContractor(int id, Contractor contractor) {
        contractorRestaurantDao.editItem(id, contractor);
    }

    public void removeContractor(int id) {
        contractorRestaurantDao.removeItemById(id);
    }

    public void removeContractor(String name) {

        final Contractor item = contractorRestaurantDao.findItem(name);
        if (item != null) {
            removeContractor(item);
        }
    }

    public void removeContractor(Contractor contractor) {
        removeContractor(contractor.getId());
    }

    //-----------------------------------PurchaseInvoice---------------------------------------------

    public boolean addPurchaseInvoice(PurchaseInvoice purchaseInvoice, Stage controlledStage) {
        purchaseInvoiceRestaurantDao.addItem(purchaseInvoice);

        purchaseInvoice.getIngredients().forEach(ingredient -> increaseAmount(ingredient, controlledStage));

        return true;
    }

    public boolean editPurchaseInvoice(PurchaseInvoice newPurchaseInvoice, Stage controlledStage) {

        for (Ingredient ingredient: subtractionWeightsOfCollectionsOfIngredients(newPurchaseInvoice))
            if (!increaseAmount(ingredient, controlledStage)) {
                return false;
            }

        purchaseInvoiceRestaurantDao.editItem(newPurchaseInvoice.getId(), newPurchaseInvoice);
        return true;
    }

    public PurchaseInvoice findPurchaseInvoice(int id) {
        return  purchaseInvoiceRestaurantDao.findItemById(id);
    }

    public List<PurchaseInvoice> findPurchaseInvoice(LocalDate fromDate, LocalDate toDate) {

        return purchaseInvoiceRestaurantDao.findItem(fromDate, toDate);
    }

    public List<PurchaseInvoice> findAllPurchaseInvoices() {
        return purchaseInvoiceRestaurantDao.findAll();
    }

    public void removePurchaseInvoice(PurchaseInvoice selectedItem, Stage controlledStage) {

        for (Ingredient ingredient: selectedItem.getIngredients()) {
            if (!decreaseAmount(ingredient, controlledStage)) return;
        }

        purchaseInvoiceRestaurantDao.removeItem(selectedItem);

    }

    //------------------------------------SalesInvoice----------------------------------------------

    public void addSalesInvoice(SalesInvoice salesInvoice, Stage controlledStage) {

        salesInvoiceRestaurantDao.addItem(salesInvoice);

        salesInvoice.getIngredients().forEach(ingredient -> decreaseAmount(ingredient, controlledStage));
    }

    public void editSalesInvoice (SalesInvoice salesInvoice, Stage controlledStage) {

        subtractionWeightsOfCollectionsOfIngredients(salesInvoice).forEach(ingredient -> decreaseAmount(ingredient, controlledStage));

        salesInvoiceRestaurantDao.editItem(salesInvoice.getId(), salesInvoice);
    }

    public SalesInvoice findSalesInvoice(int id) {
        return salesInvoiceRestaurantDao.findItemById(id);

    }

    public List<SalesInvoice> findAllSalesInvoices() {
        return salesInvoiceRestaurantDao.findAll();
    }

    public void removeSalesInvoice(SalesInvoice selectedItem, Stage controlledStage) {

        salesInvoiceRestaurantDao.removeItem(selectedItem);

        selectedItem.getIngredients().forEach(ingredient -> increaseAmount(ingredient, controlledStage));
    }

    //--------------------------------------Warehouse-----------------------------------------------

    public boolean increaseAmount(Ingredient ingredient, Stage controlledStage) {

        final WarehousePosition warehousePosition = warehousePositionRestaurantDao.findItem(new WarehousePosition(ingredient));
        if(warehousePosition != null) {
            Double ingredientWeight = warehousePosition.getIngredientAmount();
            ingredientWeight += ingredient.getIngredientWeight();
            if(ingredientWeight < 0) {
                System.out.println(warehousePosition.getIngredient() + " " + ingredientWeight);
                WarningsDialogWindow.showWindow(WarningsDialogWindow.WindowType.ERROR, "Для удаления/изменения накладной, на складе не хватает ингредиентов", controlledStage, true);
                return false;
            }

            warehousePosition.setIngredientAmount(ingredientWeight);
            warehousePositionRestaurantDao.editItem(warehousePosition.getId(), warehousePosition);
        } else {
            warehousePositionRestaurantDao.addItem(new WarehousePosition(ingredient));
        }
        return true;
    }

    public boolean decreaseAmount(Ingredient ingredient, Stage controlledStage) {

        final WarehousePosition warehousePosition = warehousePositionRestaurantDao.findItem(new WarehousePosition(ingredient));

        if(warehousePosition == null) return false;

        ingredient.setIngredientWeight(ingredient.getIngredientWeight() * -1);
        return increaseAmount(ingredient, controlledStage);
    }

    public Double checkAmount(Ingredient ingredient) {

        final WarehousePosition warehousePosition = warehousePositionRestaurantDao.findItem(new WarehousePosition(ingredient));

        if(warehousePosition == null) return 0.0D;

        return warehousePosition.getIngredientAmount();
    }

    public List<WarehousePosition> findAllPositions() {

        return warehousePositionRestaurantDao.findAll();
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Subtraction weights of ingredients which is located in new and old invoices
     * Old data of weights will be obtained from DB through invoice id
     * @param invoice invoice with new set of ingredients
     * @return collection with differences of ingredients
     */
    private List<Ingredient> subtractionWeightsOfCollectionsOfIngredients(Invoice invoice) {
        List<Ingredient> newInvoiceIngredients = invoice.getIngredients();
        List<Ingredient> oldInvoiceIngredients = invoice instanceof PurchaseInvoice ?
                purchaseInvoiceRestaurantDao.findItemById(invoice.getId()).getIngredients() :
                salesInvoiceRestaurantDao.findItemById(invoice.getId()).getIngredients();

        List<Ingredient> differentialIngredientList = new ArrayList<>();
        newInvoiceIngredients.forEach(ingredient -> differentialIngredientList.add(ingredient.clone()));

        for (Ingredient oldInvoiceIngredient : oldInvoiceIngredients) {
            if (differentialIngredientList.contains(oldInvoiceIngredient)) {
                Ingredient newInvoiceIngredient = differentialIngredientList.get(differentialIngredientList.indexOf(oldInvoiceIngredient));
                newInvoiceIngredient.setIngredientWeight(newInvoiceIngredient.getIngredientWeight() - oldInvoiceIngredient.getIngredientWeight());
            } else {
                oldInvoiceIngredient.setIngredientWeight(oldInvoiceIngredient.getIngredientWeight() * -1);
                differentialIngredientList.add(oldInvoiceIngredient);
            }
        }

        return differentialIngredientList;
    }

    //------------------------------------------------------------------------------------------------------------------

    public void setContractorRestaurantDao(RestaurantDao<Contractor> contractorRestaurantDao) {
        this.contractorRestaurantDao = contractorRestaurantDao;
    }

    public void setPurchaseInvoiceRestaurantDao(RestaurantDao<PurchaseInvoice> purchaseInvoiceRestaurantDao) {
        this.purchaseInvoiceRestaurantDao = purchaseInvoiceRestaurantDao;
    }

    public void setSalesInvoiceRestaurantDao(RestaurantDao<SalesInvoice> salesInvoiceRestaurantDao) {
        this.salesInvoiceRestaurantDao = salesInvoiceRestaurantDao;
    }

    public void setWarehousePositionRestaurantDao(RestaurantDao<WarehousePosition> warehousePositionRestaurantDao) {
        this.warehousePositionRestaurantDao = warehousePositionRestaurantDao;
    }
}
