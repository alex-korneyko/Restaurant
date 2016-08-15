package ua.in.dris4ecoder.model.dao.hibernate;

import ua.in.dris4ecoder.model.businessObjects.KitchenProcess;
import ua.in.dris4ecoder.model.businessObjects.Menu;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alex Korneyko on 15.08.2016 10:57.
 */
public class HibernateMenuDao implements RestaurantDao<Menu> {

    @Override
    public void addItem(Menu item) {

    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void editItem(int id, Menu changedItem) {

    }

    @Override
    public Menu findItemById(int id) {
        return null;
    }

    @Override
    public List<Menu> findItem(String name) {
        return null;
    }

    @Override
    public List<Menu> findItem(Menu employee) {
        return null;
    }

    @Override
    public KitchenProcess findItem(int orderId) {
        return null;
    }

    @Override
    public List<Menu> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<Menu> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @Override
    public List<Menu> findAll() {
        return null;
    }
}
