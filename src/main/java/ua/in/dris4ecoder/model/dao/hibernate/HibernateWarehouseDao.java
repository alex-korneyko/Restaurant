package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.businessObjects.WarehousePosition;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alex Korneyko on 06.09.2016 18:53.
 */
public class HibernateWarehouseDao implements RestaurantDao<WarehousePosition> {

    SessionFactory sessionFactory;

    @Override
    public void addItem(WarehousePosition item) {
        sessionFactory.getCurrentSession().save(item);
    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void removeItem(WarehousePosition item) {
        sessionFactory.getCurrentSession().remove(item);
    }

    @Override
    public void editItem(int id, WarehousePosition changedItem) {
        sessionFactory.getCurrentSession().update(changedItem);
    }

    @Override
    public WarehousePosition findItemById(int id) {
        return sessionFactory.getCurrentSession().find(WarehousePosition.class, id);
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public List<WarehousePosition> findItem(String name) {
        final Query<WarehousePosition> query =  sessionFactory.getCurrentSession().createQuery("select wp from WarehousePosition wp where wp.ingredient.name like :name");
        query.setParameter("name", name);
        return query.list();
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public List<WarehousePosition> findItem(WarehousePosition item) {
        final Query<WarehousePosition> query = sessionFactory.getCurrentSession().createQuery("select wp from WarehousePosition wp where wp.ingredient = :item");
        query.setParameter("item", item.getIngredient());
        return query.list();
    }

    @Override
    public List<WarehousePosition> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<WarehousePosition> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public List<WarehousePosition> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select wp from WarehousePosition wp").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
