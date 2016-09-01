package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.in.dris4ecoder.model.businessObjects.KitchenProcess;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alex Korneyko on 29.08.2016 16:30.
 */
public class HibernateKitchenDao implements RestaurantDao<KitchenProcess> {

    SessionFactory sessionFactory;

    @Override
    public void addItem(KitchenProcess item) {

    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void removeItem(KitchenProcess item) {
        sessionFactory.getCurrentSession().remove(item);
    }

    @Override
    public void editItem(int id, KitchenProcess changedItem) {

    }

    @Override
    public KitchenProcess findItemById(int id) {
        return null;
    }

    @Override
    public List<KitchenProcess> findItem(String name) {
        return null;
    }

    @Override
    public List<KitchenProcess> findItem(KitchenProcess item) {
        return null;
    }

    @Override
    public List<KitchenProcess> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<KitchenProcess> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @Override
    public List<KitchenProcess> findAll() {
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
