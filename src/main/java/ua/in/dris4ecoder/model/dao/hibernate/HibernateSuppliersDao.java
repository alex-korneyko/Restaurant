package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.businessObjects.Supplier;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alex Korneyko on 30.08.2016 17:06.
 */
public class HibernateSuppliersDao implements RestaurantDao<Supplier> {

    SessionFactory sessionFactory;

    @Override
    public void addItem(Supplier item) {
        Set<Supplier> suppliers = new HashSet<>(findAll());
        if(!suppliers.contains(item)) {
            sessionFactory.getCurrentSession().save(item);
        } else {
            throw new RuntimeException("Object already exist: " + item.toString());
        }
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public void removeItemById(int id) {
        final Session currentSession = sessionFactory.getCurrentSession();
        final Query query = currentSession.createQuery("delete from Supplier s where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void editItem(int id, Supplier changedItem) {
        sessionFactory.getCurrentSession().update(changedItem);
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public Supplier findItemById(int id) {
        final Session currentSession = sessionFactory.getCurrentSession();
        final Query<Supplier> query = currentSession.createQuery("select s from Supplier s where s.id = :id");
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public List<Supplier> findItem(String name) {
        final Session currentSession = sessionFactory.getCurrentSession();
        final Query<Supplier> query = currentSession.createQuery("select s from Supplier s where s.supplierName like :name");
        query.setParameter("name", name);
        return query.list();
    }

    @Override
    public List<Supplier> findItem(Supplier item) {
        return null;
    }

    @Override
    public List<Supplier> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<Supplier> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public List<Supplier> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select s from Supplier s").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
