package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.SessionFactory;
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
    public void addItem(SalesInvoice item) {

    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void removeItem(SalesInvoice item) {
        sessionFactory.getCurrentSession().remove(item);
    }

    @Override
    public void editItem(int id, SalesInvoice changedItem) {

    }

    @Override
    public SalesInvoice findItemById(int id) {
        return null;
    }

    @Override
    public List<SalesInvoice> findItem(String name) {
        return null;
    }

    @Override
    public List<SalesInvoice> findItem(SalesInvoice item) {
        return null;
    }

    @Override
    public List<SalesInvoice> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<SalesInvoice> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @Override
    public List<SalesInvoice> findAll() {
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
