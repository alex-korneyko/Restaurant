package ua.in.dris4ecoder.model.businessServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.*;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alex Korneyko on 04.08.2016 10:55.
 */
public class ServiceService implements BusinessService {

    private RestaurantDao<Order> ordersDao;
    private RestaurantDao<KitchenProcess> kitchenProcessDao;

    @Autowired
    private ManagementService managementController;

    @Transactional
    public WarehouseChangeResult addOrder(Order order) {

        SalesInvoice salesInvoice = new SalesInvoice(true, order);
        WarehouseChangeResult result = managementController.addSalesInvoice(salesInvoice, false);

        if (result.isChangeSuccessfully()) {
            order.addSalesInvoice(salesInvoice);
            ordersDao.addItem(order);
        }

        return result;
    }

    public WarehouseChangeResult editOrder(Order order) {

        //receiving old invoice for this order
        SalesInvoice salesInvoice = managementController.findSalesInvoice(order.getSalesInvoice().getId());

        //filling old invoice
        salesInvoice.fillInvoice(order);

        //attempt updating of warehouse
        WarehouseChangeResult changeResult = managementController.editSalesInvoice(salesInvoice, false);

        if (changeResult.isChangeSuccessfully()) {

            order.addSalesInvoice(salesInvoice);
            ordersDao.editItem(order.getId(), order);
        }

        return changeResult;
    }

    @Transactional
    public void removeOrder(Order order) {

        managementController.removeSalesInvoice(order.getSalesInvoice().getId(), false);

        ordersDao.removeItem(order);
    }

    @Transactional
    public void removeOrder(int id) {
        removeOrder(findOrder(id));
    }

    @Transactional
    public Order findOrder(int id) {
        final Order order = ordersDao.findItemById(id);
        if (order != null) return order;
        else throw new IllegalArgumentException("order not found");
    }

    @Transactional
    public List<Order> findOrder(OrderDishStatus orderDishStatus) {
        return ordersDao.findItem(orderDishStatus);
    }

    @Transactional
    public List<Order> findOrder(LocalDateTime start, LocalDateTime end) {
        return ordersDao.findItem(start, end);
    }

    public List<Order> getAllOrders() {

        return ordersDao.findAll();
    }

    public void setOrdersDao(RestaurantDao<Order> ordersDao) {
        this.ordersDao = ordersDao;
    }

    public void setKitchenProcessDao(RestaurantDao<KitchenProcess> kitchenProcessDao) {
        this.kitchenProcessDao = kitchenProcessDao;
    }
}
