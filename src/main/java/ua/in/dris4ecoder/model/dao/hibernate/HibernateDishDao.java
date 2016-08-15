package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.in.dris4ecoder.model.businessObjects.Dish;
import ua.in.dris4ecoder.model.businessObjects.KitchenProcess;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alex Korneyko on 15.08.2016 10:56.
 */
public class HibernateDishDao implements RestaurantDao<Dish> {

    SessionFactory sessionFactory;

    @Override
    public void addItem(Dish item) {

    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void editItem(int id, Dish changedItem) {

    }

    @Override
    public Dish findItemById(int id) {
        return null;
    }

    @Override
    public List<Dish> findItem(String name) {
        return null;
    }

    @Override
    public List<Dish> findItem(Dish employee) {
        return null;
    }

    @Override
    public KitchenProcess findItem(int orderId) {
        return null;
    }

    @Override
    public List<Dish> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<Dish> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @Override
    public List<Dish> findAll() {
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
