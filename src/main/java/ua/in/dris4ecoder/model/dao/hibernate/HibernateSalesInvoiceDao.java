package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.businessObjects.SalesInvoice;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alex Korneyko on 30.08.2016 17:31.
 */
public class HibernateSalesInvoiceDao implements RestaurantDao<SalesInvoice> {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public int addItem(SalesInvoice item) {

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
    public void removeItem(SalesInvoice item) {
        sessionFactory.getCurrentSession().remove(item);
    }

    @Override
    @Transactional
    public void editItem(int id, SalesInvoice changedItem) {
        sessionFactory.getCurrentSession().update(changedItem);
    }

    @Override
    @Transactional
    public SalesInvoice findItemById(int id) {
        SalesInvoice salesInvoice = sessionFactory.getCurrentSession().find(SalesInvoice.class, id);
        Hibernate.initialize(salesInvoice.getOrder());
        return salesInvoice;
    }

    @Override
    @Transactional
    public SalesInvoice findItem(String name) {
        return null;
    }

    @Override
    @Transactional
    public SalesInvoice findItem(SalesInvoice item) {
        return null;
    }

    @Override
    @Transactional
    public List<SalesInvoice> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    @Transactional
    public List<SalesInvoice> findItem(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        return null;
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    @Transactional
    public List<SalesInvoice> findAll() {

        return sessionFactory.getCurrentSession().createQuery("select si from SalesInvoice si").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
