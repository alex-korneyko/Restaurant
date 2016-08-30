package ua.in.dris4ecoder.controllers.businessControllers;

import ua.in.dris4ecoder.model.businessObjects.PurchaseInvoice;
import ua.in.dris4ecoder.model.businessObjects.SalesInvoice;
import ua.in.dris4ecoder.model.businessObjects.Supplier;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

/**
 * Created by Alex Korneyko on 30.08.2016 17:24.
 */
public class ManagementController implements BusinessController {

    private RestaurantDao<Supplier> supplierRestaurantDao;
    private RestaurantDao<PurchaseInvoice> purchaseInvoiceRestaurantDao;
    private RestaurantDao<SalesInvoice> salesInvoiceRestaurantDao;




    public void setSupplierRestaurantDao(RestaurantDao<Supplier> supplierRestaurantDao) {
        this.supplierRestaurantDao = supplierRestaurantDao;
    }

    public void setPurchaseInvoiceRestaurantDao(RestaurantDao<PurchaseInvoice> purchaseInvoiceRestaurantDao) {
        this.purchaseInvoiceRestaurantDao = purchaseInvoiceRestaurantDao;
    }

    public void setSalesInvoiceRestaurantDao(RestaurantDao<SalesInvoice> salesInvoiceRestaurantDao) {
        this.salesInvoiceRestaurantDao = salesInvoiceRestaurantDao;
    }
}
