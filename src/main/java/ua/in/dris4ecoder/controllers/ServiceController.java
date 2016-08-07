package ua.in.dris4ecoder.controllers;

import ua.in.dris4ecoder.model.businessObjects.KitchenProcess;
import ua.in.dris4ecoder.model.businessObjects.Order;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

/**
 * Created by Alex Korneyko on 04.08.2016 10:55.
 */
public class ServiceController implements BusinessController {

    private RestaurantDao<Order> ordersDao;
    private RestaurantDao<KitchenProcess> kitchenProcessDao;

    public void setOrdersDao(RestaurantDao<Order> ordersDao) {
        this.ordersDao = ordersDao;
    }

    public void setKitchenProcessDao(RestaurantDao<KitchenProcess> kitchenProcessDao) {
        this.kitchenProcessDao = kitchenProcessDao;
    }
}
