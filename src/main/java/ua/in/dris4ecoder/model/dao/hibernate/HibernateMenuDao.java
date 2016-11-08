package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.in.dris4ecoder.model.businessObjects.KitchenProcess;
import ua.in.dris4ecoder.model.businessObjects.Menu;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alex Korneyko on 15.08.2016 10:57.
 */
public class HibernateMenuDao implements RestaurantDao<Menu> {

    private SessionFactory sessionFactory;

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
    public void removeItem(Menu item) {
        sessionFactory.getCurrentSession().remove(item);
    }

    @Override
    public void editItem(int id, Menu changedItem) {

    }

    @Override
    public Menu findItemById(int id) {
        return null;
    }

    @Override
    public Menu findItem(String name) {
        return null;
    }

    @Override
    public Menu findItem(Menu employee) {
        return null;
    }

    @Override
    public List<Menu> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<Menu> findItem(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        return null;
    }

    @Override
    public List<Menu> findAll() {
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
