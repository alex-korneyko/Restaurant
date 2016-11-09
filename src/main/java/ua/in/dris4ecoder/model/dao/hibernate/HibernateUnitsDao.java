package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.businessObjects.Unit;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alex Korneyko on 29.08.2016 18:41.
 */
@SuppressWarnings("ALL")
public class HibernateUnitsDao implements RestaurantDao<Unit> {

    SessionFactory sessionFactory;

    @Override
    public int addItem(Unit item) {

        return 0;
    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void removeItem(Unit item) {
        sessionFactory.getCurrentSession().remove(item);
    }

    @Override
    public void editItem(int id, Unit changedItem) {

    }

    @Override
    public Unit findItemById(int id) {
        return null;
    }

    @Override
    public Unit findItem(String name) {
        final Session currentSession = sessionFactory.getCurrentSession();
        final Query<Unit> query = currentSession.createQuery("select u from Unit u where u.unitName = :name");
        query.setParameter("name", name);
        return query.uniqueResult();
    }

    @Override
    public Unit findItem(Unit item) {
        return null;
    }

    @Override
    public List<Unit> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<Unit> findItem(LocalDateTime startPeriod, LocalDateTime endPeriod) {
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
