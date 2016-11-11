package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.in.dris4ecoder.model.businessObjects.Order;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 28.08.2016 17:38.
 */
@SuppressWarnings("ALL")
public class HibernateOrderDao implements RestaurantDao<Order> {

    SessionFactory sessionFactory;

    @Override
    @Transactional
    public int addItem(Order item) {
        Set<Order> orders = new HashSet<>(findAll());
        if (!orders.contains(item)) {
            sessionFactory.getCurrentSession().persist(item);
            return (0);
        } else {
            throw new RuntimeException("Object already exist: " + item.toString());
        }
    }

    @Override
    @Transactional
    public void removeItemById(int id) {
        final Session currentSession = sessionFactory.getCurrentSession();
        final Query query = currentSession.createQuery("delete from ua.in.dris4ecoder.model.businessObjects.Order o where o.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void removeItemByName(String name) {
        throw new NotImplementedException();
    }

    @Override
    @Transactional
    public void removeItem(Order item) {
        sessionFactory.getCurrentSession().delete(item);
    }

    @Override
    @Transactional
    public void editItem(int id, Order changedItem) {
        final Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(changedItem);
    }

    @Override
    @Transactional
    public Order findItemById(int id) {
        final Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.find(Order.class, id);
    }

    @Override
    @Transactional
    public Order findItem(String name) {
        throw new NotImplementedException();
    }

    @Override
    @Transactional
    public Order findItem(Order item) {

        return sessionFactory.getCurrentSession().find(Order.class, item.getId());
    }

    @Override
    @Transactional
    public List<Order> findItem(OrderDishStatus status) {
        return findAll().stream().filter(order -> order.getStatus() == status).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Order> findItem(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        return findAll().stream().filter(order ->
                order.getDateOfCreation().isAfter(startPeriod) && order.getDateOfCreation().isBefore(endPeriod)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Order> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select o from ua.in.dris4ecoder.model.businessObjects.Order o").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
