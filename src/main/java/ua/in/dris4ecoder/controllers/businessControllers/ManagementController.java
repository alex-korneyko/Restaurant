package ua.in.dris4ecoder.controllers.businessControllers;

import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.Contractor;
import ua.in.dris4ecoder.model.businessObjects.Invoice;
import ua.in.dris4ecoder.model.businessObjects.PurchaseInvoice;
import ua.in.dris4ecoder.model.businessObjects.SalesInvoice;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.util.List;

/**
 * Created by Alex Korneyko on 30.08.2016 17:24.
 */
public class ManagementController implements BusinessController {

    private RestaurantDao<Contractor> contractorRestaurantDao;
    private RestaurantDao<PurchaseInvoice> purchaseInvoiceRestaurantDao;
    private RestaurantDao<SalesInvoice> salesInvoiceRestaurantDao;

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

    //------------------------------------------------------------------------------------------------------------------

    @Transactional
    public void addPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
        purchaseInvoiceRestaurantDao.addItem(purchaseInvoice);
    }

    @Transactional
    public void editPurchaseInvoice(PurchaseInvoice newPurchaseInvoice) {
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
}
