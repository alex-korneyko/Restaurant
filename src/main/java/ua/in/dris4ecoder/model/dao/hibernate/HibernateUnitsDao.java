package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.businessObjects.Unit;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alex Korneyko on 29.08.2016 18:41.
 */
@SuppressWarnings("ALL")
public class HibernateUnitsDao implements RestaurantDao<Unit> {

    SessionFactory sessionFactory;

    @Override
    public void addItem(Unit item) {

    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void editItem(int id, Unit changedItem) {

    }

    @Override
    public Unit findItemById(int id) {
        return null;
    }

    @Override
    public List<Unit> findItem(String name) {
        final Session currentSession = sessionFactory.getCurrentSession();
        final Query query = currentSession.createQuery("select u from Unit u where u.unitName like :name");
        query.setParameter("name", name);
        return query.list();
    }

    @Override
    public List<Unit> findItem(Unit item) {
        return null;
    }

    @Override
    public List<Unit> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<Unit> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @Override
    public List<Unit> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select u from Unit u").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
