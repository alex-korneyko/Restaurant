package ua.in.dris4ecoder.controllers.businessControllers;

import ua.in.dris4ecoder.model.businessObjects.*;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

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

    public List<Contractor> findContractor(String contractorName) {
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
        final List<Contractor> item = contractorRestaurantDao.findItem(name);
        item.forEach(contractor -> removeContractor(contractor.getId()));
    }

    public void removeContractor(Contractor contractor) {
        removeContractor(contractor.getId());
    }

    //-----------------------------------PurchaseInvoice---------------------------------------------

    public void addPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
        purchaseInvoiceRestaurantDao.addItem(purchaseInvoice);

        purchaseInvoice.getIngredients().forEach(this::increaseAmount);
    }

    public void editPurchaseInvoice(PurchaseInvoice newPurchaseInvoice) {

        subtractionWeightsOfCollectionsOfIngredients(newPurchaseInvoice).forEach(this::increaseAmount);

        purchaseInvoiceRestaurantDao.editItem(newPurchaseInvoice.getId(), newPurchaseInvoice);

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

    public void removePurchaseInvoice(PurchaseInvoice selectedItem) {
        purchaseInvoiceRestaurantDao.removeItem(selectedItem);

        selectedItem.getIngredients().forEach(this::decreaseAmount);
    }

    //------------------------------------SalesInvoice----------------------------------------------

    public void addSalesInvouce(SalesInvoice salesInvoice) {

        salesInvoiceRestaurantDao.addItem(salesInvoice);

        salesInvoice.getIngredients().forEach(this::decreaseAmount);
    }

    public void editSalesInvoice (SalesInvoice salesInvoice) {

        subtractionWeightsOfCollectionsOfIngredients(salesInvoice).forEach(this::decreaseAmount);

        salesInvoiceRestaurantDao.editItem(salesInvoice.getId(), salesInvoice);
    }

    public SalesInvoice findSalesInvoice(int id) {
        return salesInvoiceRestaurantDao.findItemById(id);

    }

    public List<SalesInvoice> findAllSalesInvoices() {
        return salesInvoiceRestaurantDao.findAll();
    }

    public void removeSalesInvoice(SalesInvoice selectedItem) {

        salesInvoiceRestaurantDao.removeItem(selectedItem);

        selectedItem.getIngredients().forEach(this::increaseAmount);
    }

    //--------------------------------------Warehouse-----------------------------------------------

    public void increaseAmount(Ingredient ingredient) {

        final List<WarehousePosition> warehousePositions = warehousePositionRestaurantDao.findItem(new WarehousePosition(ingredient));
        if(warehousePositions.size() > 0) {
            final WarehousePosition warehousePosition = warehousePositions.get(0);
            Double ingredientWeight = warehousePosition.getIngredientAmount();
            ingredientWeight += ingredient.getIngredientWeight();
            if(ingredientWeight < 0.0001)
                ingredientWeight = 0.0;

            warehousePosition.setIngredientAmount(ingredientWeight);
            warehousePositionRestaurantDao.editItem(warehousePosition.getId(), warehousePosition);
        } else {
            warehousePositionRestaurantDao.addItem(new WarehousePosition(ingredient));
        }
    }

    public void decreaseAmount(Ingredient ingredient) {

        final List<WarehousePosition> warehousePositions = warehousePositionRestaurantDao.findItem(new WarehousePosition(ingredient));

        if(warehousePositions.isEmpty()) return;

        ingredient.setIngredientWeight(ingredient.getIngredientWeight() * -1);
        increaseAmount(ingredient);
    }

    public Double checkAmount(Ingredient ingredient) {

        final List<WarehousePosition> warehousePositions = warehousePositionRestaurantDao.findItem(new WarehousePosition(ingredient));

        if(warehousePositions.isEmpty()) return 0.0;

        double ingredientAmount = warehousePositions.get(0).getIngredientAmount();

        return ingredientAmount;
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
