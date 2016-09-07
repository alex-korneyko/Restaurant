package ua.in.dris4ecoder.controllers.businessControllers;

import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.*;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 30.08.2016 17:24.
 */
public class ManagementController implements BusinessController {

    private RestaurantDao<Contractor> contractorRestaurantDao;
    private RestaurantDao<PurchaseInvoice> purchaseInvoiceRestaurantDao;
    private RestaurantDao<SalesInvoice> salesInvoiceRestaurantDao;
    private RestaurantDao<WarehousePosition> warehousePositionRestaurantDao;

    //--------------------------------------Contractor------------------------------------------------
    @Transactional
    public void addContractor(Contractor contractor) {
        contractorRestaurantDao.addItem(contractor);
    }

    @Transactional
    public void addContractor(String contractorName) {
        addContractor(new Contractor(contractorName));
    }

    @Transactional
    public List<Contractor> findContractor(String contractorName) {
        return contractorRestaurantDao.findItem(contractorName);
    }

    @Transactional
    public List<Contractor> findAllContractors() {
        return contractorRestaurantDao.findAll();
    }

    @Transactional
    public void editContractor(int id, Contractor contractor) {
        contractorRestaurantDao.editItem(id, contractor);
    }

    @Transactional
    public void removeContractor(int id) {
        contractorRestaurantDao.removeItemById(id);
    }

    @Transactional
    public void removeContractor(String name) {
        final List<Contractor> item = contractorRestaurantDao.findItem(name);
        item.forEach(contractor -> removeContractor(contractor.getId()));
    }

    @Transactional
    public void removeContractor(Contractor contractor) {
        removeContractor(contractor.getId());
    }

    //-----------------------------------PurchaseInvoice---------------------------------------------

    @Transactional
    public void addPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
        purchaseInvoiceRestaurantDao.addItem(purchaseInvoice);

        purchaseInvoice.getIngredients().forEach(this::increaseAmount);
    }

    @Transactional
    public void editPurchaseInvoice(PurchaseInvoice newPurchaseInvoice) {

        subtractionWeightsOfCollectionsOfIngredients(newPurchaseInvoice).forEach(this::increaseAmount);

        // TODO: 07.09.2016 Throwing exception: Batch update returned unexpected row count from update [0]; actual row count: 2; expected: 1
        // TODO: it occur if line above is commented.
        purchaseInvoiceRestaurantDao.editItem(newPurchaseInvoice.getId(), newPurchaseInvoice);

    }

    @Transactional
    public PurchaseInvoice findPurchaseInvoice(int id) {
        return  purchaseInvoiceRestaurantDao.findItemById(id);
    }

    @Transactional
    public List<PurchaseInvoice> findAllPurchaseInvoices() {
        return purchaseInvoiceRestaurantDao.findAll();
    }

    @Transactional
    public void removePurchaseInvoice(PurchaseInvoice selectedItem) {
        purchaseInvoiceRestaurantDao.removeItem(selectedItem);

        selectedItem.getIngredients().forEach(this::decreaseAmount);
    }

    //------------------------------------SalesInvoice----------------------------------------------

    @Transactional
    public void addSalesInvouce(SalesInvoice salesInvoice) {

        salesInvoiceRestaurantDao.addItem(salesInvoice);

        salesInvoice.getIngredients().forEach(this::decreaseAmount);
    }

    @Transactional
    public void editSalesInvoice (SalesInvoice salesInvoice) {

        subtractionWeightsOfCollectionsOfIngredients(salesInvoice).forEach(this::decreaseAmount);

        salesInvoiceRestaurantDao.editItem(salesInvoice.getId(), salesInvoice);
    }

    @Transactional
    public SalesInvoice findSalesInvoice(int id) {
        return salesInvoiceRestaurantDao.findItemById(id);

    }

    @Transactional
    public List<SalesInvoice> findAllSalesInvoices() {
        return salesInvoiceRestaurantDao.findAll();
    }

    @Transactional
    public void removeSalesInvoice(SalesInvoice selectedItem) {

        salesInvoiceRestaurantDao.removeItem(selectedItem);

        selectedItem.getIngredients().forEach(this::increaseAmount);
    }

    //--------------------------------------Warehouse-----------------------------------------------

//    @Transactional
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

    @Transactional
    public void decreaseAmount(Ingredient ingredient) {

        final List<WarehousePosition> warehousePositions = warehousePositionRestaurantDao.findItem(new WarehousePosition(ingredient));

        if(warehousePositions.isEmpty()) return;

        ingredient.setIngredientWeight(ingredient.getIngredientWeight() * -1);
        increaseAmount(ingredient);
    }

    @Transactional
    public Double checkAmount(Ingredient ingredient) {

        final List<WarehousePosition> warehousePositions = warehousePositionRestaurantDao.findItem(new WarehousePosition(ingredient));

        if(warehousePositions.isEmpty()) return 0.0;

        return warehousePositions.get(0).getIngredient().getIngredientWeight();
    }

    public List<Ingredient> findAllPositions() {

        return warehousePositionRestaurantDao.findAll().stream().map(WarehousePosition::getIngredient).collect(Collectors.toList());
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
