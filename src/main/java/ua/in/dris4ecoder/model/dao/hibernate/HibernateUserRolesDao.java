package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.businessObjects.UserRole;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by admin on 02.12.2016.
 */
public class HibernateUserRolesDao implements RestaurantDao<UserRole> {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public int addItem(UserRole item) {

        Serializable save = sessionFactory.getCurrentSession().save(item);

        return save == null ? 0 : 1;
    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    @Transactional
    public void removeItemByName(String name) {

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.remove(new UserRole(name));
    }

    @Override
    public void removeItem(UserRole item) {

    }

    @Override
    public void editItem(int id, UserRole changedItem) {

    }

    @Override
    public UserRole findItemById(int id) {
        return null;
    }

    @SuppressWarnings("JpaQlInspection" )
    @Override
    @Transactional
    public UserRole findItem(String name) {

        Session currentSession = sessionFactory.getCurrentSession();
        Query<UserRole> query = currentSession.createQuery("select r from UserRole r where r.roleName = :name" );
        query.setParameter("name", name);

        return query.uniqueResult();
    }

    @Override
    public UserRole findItem(UserRole item) {
        return null;
    }

    @Override
    public List<UserRole> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<UserRole> findItem(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        return null;
    }

    @Override
    public List<UserRole> findAll() {
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
