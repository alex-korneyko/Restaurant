package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.businessObjects.SalesInvoice;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alex Korneyko on 30.08.2016 17:31.
 */
public class HibernateSalesInvoiceDao implements RestaurantDao<SalesInvoice> {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addItem(SalesInvoice item) {

        sessionFactory.getCurrentSession().save(item);
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
        return sessionFactory.getCurrentSession().find(SalesInvoice.class, id);
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
    public List<SalesInvoice> findItem(LocalDate startPeriod, LocalDate endPeriod) {
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
