package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.in.dris4ecoder.model.businessObjects.Contractor;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alex Korneyko on 30.08.2016 17:06.
 */
public class HibernateContractorsDao implements RestaurantDao<Contractor> {

    SessionFactory sessionFactory;

    @Override
    public void addItem(Contractor item) {
        Set<Contractor> contractors = new HashSet<>(findAll());
        if(!contractors.contains(item)) {
            sessionFactory.getCurrentSession().save(item);
        } else {
            throw new RuntimeException("Object already exist: " + item.toString());
        }
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public void removeItemById(int id) {
        final Session currentSession = sessionFactory.getCurrentSession();
        final Query query = currentSession.createQuery("delete from Contractor c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void editItem(int id, Contractor changedItem) {
        sessionFactory.getCurrentSession().update(changedItem);
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public Contractor findItemById(int id) {
        final Session currentSession = sessionFactory.getCurrentSession();
        final Query<Contractor> query = currentSession.createQuery("select c from Contractor c where c.id = :id");
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public List<Contractor> findItem(String name) {
        final Session currentSession = sessionFactory.getCurrentSession();
        final Query<Contractor> query = currentSession.createQuery("select c from Contractor c where c.contractorName like :name");
        query.setParameter("name", name);
        return query.list();
    }

    @Override
    public List<Contractor> findItem(Contractor item) {
        return null;
    }

    @Override
    public List<Contractor> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<Contractor> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public List<Contractor> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select c from Contractor c").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
