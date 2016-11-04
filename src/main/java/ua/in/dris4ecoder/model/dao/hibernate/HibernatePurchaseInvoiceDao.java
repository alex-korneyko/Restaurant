package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void addItem(PurchaseInvoice item) {

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
    public void removeItem(PurchaseInvoice item) {
        sessionFactory.getCurrentSession().remove(item);
    }

    @Override
    @Transactional
    public void editItem(int id, PurchaseInvoice changedItem) {

        sessionFactory.getCurrentSession().update(changedItem);
    }

    @Override
    @Transactional
    public PurchaseInvoice findItemById(int id) {
        final PurchaseInvoice purchaseInvoice = sessionFactory.getCurrentSession().find(PurchaseInvoice.class, id);
        return purchaseInvoice;
    }

    @Override
    @Transactional
    public PurchaseInvoice findItem(String name) {
        return null;
    }

    @Override
    @Transactional
    public PurchaseInvoice findItem(PurchaseInvoice item) {
        return null;
    }

    @Override
    @Transactional
    public List<PurchaseInvoice> findItem(OrderDishStatus status) {
        return null;
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    @Transactional
    public List<PurchaseInvoice> findItem(LocalDate startPeriod, LocalDate endPeriod) {

        Query<PurchaseInvoice> query = sessionFactory.getCurrentSession().createQuery("select pi from PurchaseInvoice pi where pi.invoiceDate >= :startPeriod and pi.invoiceDate <= :endPeriod");
        query.setParameter("startPeriod", startPeriod);
        query.setParameter("endPeriod", endPeriod);
        return query.list();
    }

    @Override
    @Transactional
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
