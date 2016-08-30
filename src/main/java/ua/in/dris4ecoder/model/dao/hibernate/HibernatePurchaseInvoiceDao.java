package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.SessionFactory;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.businessObjects.PurchaseInvoice;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alex Korneyko on 30.08.2016 17:30.
 */
public class HibernatePurchaseInvoiceDao implements RestaurantDao<PurchaseInvoice> {

    private SessionFactory sessionFactory;

    @Override
    public void addItem(PurchaseInvoice item) {

    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void editItem(int id, PurchaseInvoice changedItem) {

    }

    @Override
    public PurchaseInvoice findItemById(int id) {
        return null;
    }

    @Override
    public List<PurchaseInvoice> findItem(String name) {
        return null;
    }

    @Override
    public List<PurchaseInvoice> findItem(PurchaseInvoice item) {
        return null;
    }

    @Override
    public List<PurchaseInvoice> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<PurchaseInvoice> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @Override
    public List<PurchaseInvoice> findAll() {
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
