package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alex Korneyko on 05.09.2016 20:20.
 */
public class HibernateWarehouseDao implements RestaurantDao<Ingredient> {

    SessionFactory sessionFactory;

    @Override
    public void addItem(Ingredient item) {

        sessionFactory.getCurrentSession().save(item);
    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void removeItem(Ingredient item) {

    }

    @Override
    public void editItem(int id, Ingredient changedItem) {

    }

    @Override
    public Ingredient findItemById(int id) {
        return null;
    }

    @Override
    public List<Ingredient> findItem(String name) {
        return null;
    }

    @Override
    public List<Ingredient> findItem(Ingredient item) {
        return null;
    }

    @Override
    public List<Ingredient> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<Ingredient> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @Override
    public List<Ingredient> findAll() {
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
