package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.businessObjects.WarehousePosition;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alex Korneyko on 06.09.2016 18:53.
 */
public class HibernateWarehouseDao implements RestaurantDao<WarehousePosition> {

    SessionFactory sessionFactory;

    @Override
    @Transactional
    public int addItem(WarehousePosition item) {
        Serializable save = sessionFactory.getCurrentSession().save(item);
        return ((int) save);
    }

    @Override
    @Transactional
    public void removeItemById(int id) {

    }

    @Override
    @Transactional
    public void removeItemByName(String name) {

    }

    @Override
    @Transactional
    public void removeItem(WarehousePosition item) {
        sessionFactory.getCurrentSession().remove(item);
    }

    @Override
    @Transactional
    public void editItem(int id, WarehousePosition changedItem) {
        sessionFactory.getCurrentSession().update(changedItem);
    }

    @Override
    @Transactional
    public WarehousePosition findItemById(int id) {
        return sessionFactory.getCurrentSession().find(WarehousePosition.class, id);
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    @Transactional
    public WarehousePosition findItem(String name) {
        final Query<WarehousePosition> query =  sessionFactory.getCurrentSession().createQuery("select wp from WarehousePosition wp where wp.ingredient.name = :name");
        query.setParameter("name", name);
        return query.uniqueResult();
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    @Transactional
    public WarehousePosition findItem(WarehousePosition item) {
        final Query<WarehousePosition> query = sessionFactory.getCurrentSession().createQuery("select wp from WarehousePosition wp where wp.ingredient = :item");
        query.setParameter("item", item.getIngredient());
        return query.uniqueResult();
    }

    @Override
    @Transactional
    public List<WarehousePosition> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    @Transactional
    public List<WarehousePosition> findItem(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        return null;
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    @Transactional
    public List<WarehousePosition> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select wp from WarehousePosition wp").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
