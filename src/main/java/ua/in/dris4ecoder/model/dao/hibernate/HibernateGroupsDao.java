package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.businessObjects.UserGroup;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alex Korneyko on 28.10.2016 20:48.
 */
public class HibernateGroupsDao implements RestaurantDao<UserGroup> {

    private SessionFactory sessionFactory;

    @Override
    public void addItem(UserGroup item) {

    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void removeItem(UserGroup item) {

    }

    @Override
    public void editItem(int id, UserGroup changedItem) {

    }

    @Override
    public UserGroup findItemById(int id) {
        return null;
    }

    @Override
    @Transactional
    public List<UserGroup> findItem(String name) {
        Session currentSession = sessionFactory.getCurrentSession();

        @SuppressWarnings("JpaQlInspection")
        Query<UserGroup> query = currentSession.createQuery("select g from UserGroup g where g.groupName = :name");
        query.setParameter("name", name);

        return query.list();
    }

    @Override
    public List<UserGroup> findItem(UserGroup item) {
        return null;
    }

    @Override
    public List<UserGroup> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<UserGroup> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @Override
    public List<UserGroup> findAll() {
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
