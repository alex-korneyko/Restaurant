package ua.in.dris4ecoder.controllers.businessControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.*;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alex Korneyko on 04.08.2016 10:55.
 */
public class ServiceController implements BusinessController {

    private RestaurantDao<Order> ordersDao;
    private RestaurantDao<KitchenProcess> kitchenProcessDao;

    @Autowired
    private ManagementController managementController;

    @Transactional
    public WarehouseChangeResult addOrder(Order order) {

        SalesInvoice salesInvoice = new SalesInvoice(true, order);
        WarehouseChangeResult result = managementController.addSalesInvoice(salesInvoice, null);

        if (result.isChangeSuccessfully()) {
            int orderId = ordersDao.addItem(order);
            salesInvoice = managementController.findSalesInvoice(result.getInvoiceId());
            salesInvoice.setOrder(ordersDao.findItemById(orderId));
            managementController.editSalesInvoice(salesInvoice, null);
        }

        return result;
    }

    @Transactional
    public void removeOrder(Order order) {
        if (order.getStatus() == OrderDishStatus.IN_QUEUE) {
            ordersDao.removeItemById(order.getId());
        } else {
            throw new RuntimeException("Order already " + order.getStatus().toString());
        }
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

    @Transactional
    public void editOrder(Order order) {
        if (order.getStatus() == OrderDishStatus.IN_QUEUE) {
            ordersDao.editItem(order.getId(), order);
        } else {
            throw new RuntimeException("Order already " + order.getStatus().toString());
        }
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
