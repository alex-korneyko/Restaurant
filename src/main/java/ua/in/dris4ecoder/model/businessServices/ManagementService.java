package ua.in.dris4ecoder.model.businessServices;

import ua.in.dris4ecoder.model.businessObjects.*;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Korneyko on 30.08.2016 17:24.
 */
public class ManagementService implements BusinessService {

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

    public WarehouseChangeResult addPurchaseInvoice(PurchaseInvoice purchaseInvoice, boolean saveToDb) {

        WarehouseChangeResult changeResult = warehouseUpdate(purchaseInvoice);

        if (changeResult.isChangeSuccessfully() && saveToDb) {
            purchaseInvoiceRestaurantDao.addItem(purchaseInvoice);
        }

        return changeResult;
    }



    public WarehouseChangeResult editPurchaseInvoice(PurchaseInvoice newPurchaseInvoice, boolean saveChangedInvoiceToDb) {

        PurchaseInvoice differencePurchaseInvoice = (PurchaseInvoice) subtractionWeightsOfCollectionsOfIngredients(newPurchaseInvoice);
        WarehouseChangeResult result = addPurchaseInvoice(differencePurchaseInvoice, false);

        if (result.isChangeSuccessfully() && saveChangedInvoiceToDb) {
            purchaseInvoiceRestaurantDao.editItem(newPurchaseInvoice.getId(), newPurchaseInvoice);
        }

        return result;
    }

    public PurchaseInvoice findPurchaseInvoice(int id) {
        return purchaseInvoiceRestaurantDao.findItemById(id);
    }

    public List<PurchaseInvoice> findPurchaseInvoice(LocalDateTime fromDate, LocalDateTime toDate) {

        return purchaseInvoiceRestaurantDao.findItem(fromDate, toDate);
    }

    public List<PurchaseInvoice> findAllPurchaseInvoices() {
        return purchaseInvoiceRestaurantDao.findAll();
    }

    public WarehouseChangeResult removePurchaseInvoice(int purchaseInvoiceId, boolean deleteFromDb) {

        PurchaseInvoice purchaseInvoice = purchaseInvoiceRestaurantDao.findItemById(purchaseInvoiceId);

        return removePurchaseInvoice(purchaseInvoice, deleteFromDb);
    }

    public WarehouseChangeResult removePurchaseInvoice(PurchaseInvoice purchaseInvoice, boolean deleteFromDb) {

        SalesInvoice salesInvoice = new SalesInvoice();
        salesInvoice.setIngredients(purchaseInvoice.getIngredients());
        WarehouseChangeResult changeResult = warehouseUpdate(salesInvoice);

        if (changeResult.isChangeSuccessfully() && deleteFromDb) {
            purchaseInvoiceRestaurantDao.removeItem(purchaseInvoice);
        }

        return changeResult;
    }

    //------------------------------------SalesInvoice----------------------------------------------

    public WarehouseChangeResult addSalesInvoice(SalesInvoice salesInvoice, boolean saveInvoiceInDB) {

        WarehouseChangeResult changeResult = warehouseUpdate(salesInvoice);

        if (changeResult.isChangeSuccessfully() && saveInvoiceInDB) {
            salesInvoiceRestaurantDao.addItem(salesInvoice);
        }

        return changeResult;
    }

    public WarehouseChangeResult editSalesInvoice(SalesInvoice salesInvoice, boolean saveChangedInvoiceToDb) {

        SalesInvoice differenceSalesInvoice = (SalesInvoice) subtractionWeightsOfCollectionsOfIngredients(salesInvoice);
        WarehouseChangeResult changeResult = addSalesInvoice(differenceSalesInvoice, false);

        if (changeResult.isChangeSuccessfully() && saveChangedInvoiceToDb) {
            salesInvoiceRestaurantDao.editItem(salesInvoice.getId(), salesInvoice);
        }

        return changeResult;
    }

    public SalesInvoice findSalesInvoice(int id) {
        return salesInvoiceRestaurantDao.findItemById(id);

    }

    public List<SalesInvoice> findAllSalesInvoices() {
        return salesInvoiceRestaurantDao.findAll();
    }

    public WarehouseChangeResult removeSalesInvoice(int invoiceId, boolean deleteFromDb) {

        SalesInvoice salesInvoice = salesInvoiceRestaurantDao.findItemById(invoiceId);

        return removeSalesInvoice(salesInvoice, deleteFromDb);
    }

    public WarehouseChangeResult removeSalesInvoice(SalesInvoice salesInvoice, boolean deleteFromDb) {

        PurchaseInvoice purchaseInvoice = new PurchaseInvoice();
        purchaseInvoice.setIngredients(salesInvoice.getIngredients());

        WarehouseChangeResult changeResult = warehouseUpdate(purchaseInvoice);

        if (changeResult.isChangeSuccessfully() && deleteFromDb) {
            salesInvoiceRestaurantDao.removeItem(salesInvoice);
        }

        return changeResult;
    }

    public List<WarehousePosition> findAllPositions() {

        return warehousePositionRestaurantDao.findAll();
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Subtraction weights of ingredients which is located in new and old invoices
     * Old data of weights will be obtained from DB through invoice id
     *
     * @param invoice invoice with new set of ingredients
     * @return collection with differences of ingredients
     */
    private Invoice subtractionWeightsOfCollectionsOfIngredients(Invoice invoice) {
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

        if (invoice instanceof PurchaseInvoice) {
            PurchaseInvoice purchaseInvoice = new PurchaseInvoice();
            purchaseInvoice.setIngredients(differentialIngredientList);
            return purchaseInvoice;
        } else {
            SalesInvoice salesInvoice = new SalesInvoice();
            salesInvoice.setIngredients(differentialIngredientList);
            return salesInvoice;
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private WarehouseChangeResult warehouseUpdate(Invoice invoice) {

        WarehouseChangeResult result = checkAvailability(invoice);

        if (!result.isChangeSuccessfully()) {
            return result;
        }

        for (Ingredient ingredient : invoice.getIngredients()) {
            WarehousePosition position = warehousePositionRestaurantDao.findItem(ingredient.getIngredientName());
            if (invoice instanceof PurchaseInvoice) {
                if (position == null) {
                    warehousePositionRestaurantDao.addItem(new WarehousePosition(ingredient));
                } else {
                    position.setIngredientAmount(position.getIngredientAmount() + ingredient.getIngredientWeight());
                    warehousePositionRestaurantDao.editItem(position.getId(), position);
                }
            } else {
                if (position == null) {
                    if (ingredient.getIngredientWeight() < 0) {
                        ingredient.setIngredientWeight(ingredient.getIngredientWeight() * (-1));
                        warehousePositionRestaurantDao.addItem(new WarehousePosition(ingredient));
                    }
                } else {
                    position.setIngredientAmount(position.getIngredientAmount() - ingredient.getIngredientWeight());
                    warehousePositionRestaurantDao.editItem(position.getId(), position);
                }
            }
        }

        return result;
    }

    private WarehouseChangeResult checkAvailability(Invoice invoice) {

        for (Ingredient ingredient : invoice.getIngredients()) {
            WarehousePosition position = warehousePositionRestaurantDao.findItem(ingredient.getIngredientName());
            if (invoice instanceof PurchaseInvoice) {
                if (position == null) {
                    if (ingredient.getIngredientWeight() < 0) {
                        return new WarehouseChangeResult(false, ingredient, "Не хватает ингредиентов на складе для данной операции");
                    }
                } else {
                    if (position.getIngredientAmount() < (ingredient.getIngredientWeight() * (-1))) {
                        return new WarehouseChangeResult(false, ingredient, "Не хватает ингредиентов на складе для данной операции");
                    }
                }
            } else {
                if (position == null) {
                    if (ingredient.getIngredientWeight() > 0) {
                        return new WarehouseChangeResult(false, ingredient, "Не хватает ингредиентов на складе для данной операции");
                    }
                } else {
                    if (position.getIngredientAmount() < ingredient.getIngredientWeight()) {
                        return new WarehouseChangeResult(false, ingredient, "Не хватает ингредиентов на складе для данной операции");
                    }
                }
            }
        }

        return new WarehouseChangeResult(true, invoice.getId());
    }

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
