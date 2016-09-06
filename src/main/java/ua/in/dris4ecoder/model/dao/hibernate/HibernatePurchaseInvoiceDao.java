package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

        sessionFactory.getCurrentSession().save(item);
    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void removeItem(PurchaseInvoice item) {
        sessionFactory.getCurrentSession().remove(item);
    }

    @Override
    public void editItem(int id, PurchaseInvoice changedItem) {

        sessionFactory.getCurrentSession().update(changedItem);
    }

    @Override
    public PurchaseInvoice findItemById(int id) {
        final PurchaseInvoice purchaseInvoice = sessionFactory.getCurrentSession().find(PurchaseInvoice.class, id);
        return purchaseInvoice;
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
    @SuppressWarnings("JpaQlInspection")
    public List<PurchaseInvoice> findAll() {
        final Query<PurchaseInvoice> query = sessionFactory.getCurrentSession().createQuery("select pi from PurchaseInvoice pi");
        final List<PurchaseInvoice> list = query.list();
        return list;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
