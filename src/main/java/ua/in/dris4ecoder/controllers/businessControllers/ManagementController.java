package ua.in.dris4ecoder.controllers.businessControllers;

import javafx.stage.Stage;
import ua.in.dris4ecoder.model.businessObjects.*;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public Contractor findContractor(int contractorId) {

        return contractorRestaurantDao.findItemById(contractorId);
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

    public WarehouseChangeResult addPurchaseInvoice(PurchaseInvoice purchaseInvoice, Stage controlledStage) {
        purchaseInvoiceRestaurantDao.addItem(purchaseInvoice);

        purchaseInvoice.getIngredients().forEach(this::increaseAmount);

        WarehouseChangeResult result = new WarehouseChangeResult();
        result.setMessage("Успешно");

        return result;
    }

    public WarehouseChangeResult editPurchaseInvoice(PurchaseInvoice newPurchaseInvoice, Stage controlledStage) {

        for (Ingredient ingredient: subtractionWeightsOfCollectionsOfIngredients(newPurchaseInvoice)) {
            WarehouseChangeResult result = increaseAmount(ingredient);
            if (!result.isChangeSuccessfully()) {
                return result;
            }
        }

        purchaseInvoiceRestaurantDao.editItem(newPurchaseInvoice.getId(), newPurchaseInvoice);

        return new WarehouseChangeResult(true, null, "Удачно");
    }

    public PurchaseInvoice findPurchaseInvoice(int id) {
        return  purchaseInvoiceRestaurantDao.findItemById(id);
    }

    public List<PurchaseInvoice> findPurchaseInvoice(LocalDateTime fromDate, LocalDateTime toDate) {

        return purchaseInvoiceRestaurantDao.findItem(fromDate, toDate);
    }

    public List<PurchaseInvoice> findAllPurchaseInvoices() {
        return purchaseInvoiceRestaurantDao.findAll();
    }

    public WarehouseChangeResult removePurchaseInvoice(int purchaseInvoiceId) {

        PurchaseInvoice purchaseInvoice = purchaseInvoiceRestaurantDao.findItemById(purchaseInvoiceId);

        return removePurchaseInvoice(purchaseInvoice, null);
    }

    public WarehouseChangeResult removePurchaseInvoice(PurchaseInvoice selectedItem, Stage controlledStage) {

        for (Ingredient ingredient: selectedItem.getIngredients()) {
            WarehouseChangeResult warehouseChangeResult = decreaseAmount(ingredient);
            if (!warehouseChangeResult.isChangeSuccessfully()) return warehouseChangeResult;
        }

        purchaseInvoiceRestaurantDao.removeItem(selectedItem);

        return new WarehouseChangeResult(true);
    }

    //------------------------------------SalesInvoice----------------------------------------------

    public WarehouseChangeResult addSalesInvoice(SalesInvoice salesInvoice, boolean saveInvoiceInDB) {

        for (Ingredient ingredient : salesInvoice.getIngredients()) {
            WarehouseChangeResult result = decreaseAmount(ingredient);
            if (!result.isChangeSuccessfully()) return result;
        }

        int invoiceId = 0;

        if (saveInvoiceInDB) {
            invoiceId = salesInvoiceRestaurantDao.addItem(salesInvoice);
        }

        return new WarehouseChangeResult(true, invoiceId);
    }

    public WarehouseChangeResult editSalesInvoice (SalesInvoice salesInvoice, boolean saveChangedInvoiceToDb) {

        SalesInvoice differenceOfSalesInvoice = new SalesInvoice();
        differenceOfSalesInvoice.setIngredients(subtractionWeightsOfCollectionsOfIngredients(salesInvoice));
        WarehouseChangeResult warehouseChangeResult = addSalesInvoice(differenceOfSalesInvoice, false);

        if (saveChangedInvoiceToDb) {
            salesInvoiceRestaurantDao.editItem(salesInvoice.getId(), salesInvoice);
        }

        return warehouseChangeResult;
    }

    public SalesInvoice findSalesInvoice(int id) {
        return salesInvoiceRestaurantDao.findItemById(id);

    }

    public List<SalesInvoice> findAllSalesInvoices() {
        return salesInvoiceRestaurantDao.findAll();
    }

    public WarehouseChangeResult removeSalesInvoice(int invoiceId, boolean deleteInvoiceInDB) {

        SalesInvoice itemById = salesInvoiceRestaurantDao.findItemById(invoiceId);

        if (itemById != null) {
            return removeSalesInvoice(itemById, deleteInvoiceInDB);
        } else {
            return new WarehouseChangeResult(false, null, "Накладная не найдена");
        }
    }

    public WarehouseChangeResult removeSalesInvoice(SalesInvoice selectedItem, boolean deleteInvoiceInDB) {

        for (Ingredient ingredient: selectedItem.getIngredients()) {

            WarehouseChangeResult result = increaseAmount(ingredient);
            if (!result.isChangeSuccessfully()) return result;
        }

        if (deleteInvoiceInDB) {
            salesInvoiceRestaurantDao.removeItem(selectedItem);
        }

        return new WarehouseChangeResult(true, null, "Удачно");
    }

    //--------------------------------------Warehouse-----------------------------------------------

    public WarehouseChangeResult increaseAmount(Ingredient ingredient) {

        WarehouseChangeResult result = new WarehouseChangeResult();

        final WarehousePosition warehousePosition = warehousePositionRestaurantDao.findItem(new WarehousePosition(ingredient));
        if(warehousePosition != null) {
            Double ingredientWeight = warehousePosition.getIngredientAmount();
            ingredientWeight += ingredient.getIngredientWeight();
            if(ingredientWeight < 0) {
                System.out.println(warehousePosition.getIngredient() + " " + ingredientWeight);
                result.setChangeSuccessfully(false);
                result.setMessage("На складе нет необходимого количества '" + ingredient.getIngredientName() + "' для данного действия");
                result.setIngredient(warehousePosition.getIngredient());
                return result;
            }

            warehousePosition.setIngredientAmount(ingredientWeight);
            warehousePositionRestaurantDao.editItem(warehousePosition.getId(), warehousePosition);
        } else {
            warehousePositionRestaurantDao.addItem(new WarehousePosition(ingredient));
        }
        result.setChangeSuccessfully(true);
        result.setMessage("Изменение успешно");
        result.setIngredient(warehousePositionRestaurantDao.findItem(new WarehousePosition(ingredient)).getIngredient());

        return result;
    }

    private WarehouseChangeResult decreaseAmount(Ingredient ingredient) {

        WarehouseChangeResult result = new WarehouseChangeResult();

        final WarehousePosition warehousePosition = warehousePositionRestaurantDao.findItem(new WarehousePosition(ingredient));

        if(warehousePosition == null) {
            result.setChangeSuccessfully(false);
            ingredient.setIngredientWeight(0.0D);
            result.setIngredient(ingredient);
            result.setMessage("На складе нет необходимого количества '" + ingredient.getIngredientName() + "' для данного действия");
            return result;
        }

        ingredient.setIngredientWeight(ingredient.getIngredientWeight() * -1);
        return increaseAmount(ingredient);
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
