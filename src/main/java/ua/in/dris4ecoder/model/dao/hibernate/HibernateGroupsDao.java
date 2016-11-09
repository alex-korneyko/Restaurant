package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.businessObjects.UserGroup;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alex Korneyko on 28.10.2016 20:48.
 */
public class HibernateGroupsDao implements RestaurantDao<UserGroup> {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public int addItem(UserGroup item) {

        return 0;
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
    public void removeItem(UserGroup item) {

    }

    @Override
    @Transactional
    public void editItem(int id, UserGroup changedItem) {

    }

    @Override
    @Transactional
    public UserGroup findItemById(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        return currentSession.get(UserGroup.class, id);
    }

    @Override
    @Transactional
    public UserGroup findItem(String name) {
        Session currentSession = sessionFactory.getCurrentSession();

        @SuppressWarnings("JpaQlInspection")
        Query<UserGroup> query = currentSession.createQuery("select g from UserGroup g where g.groupName = :name");
        query.setParameter("name", name);

        return query.uniqueResult();
    }

    @Override
    @Transactional
    public UserGroup findItem(UserGroup item) {
        return null;
    }

    @Override
    @Transactional
    public List<UserGroup> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    @Transactional
    public List<UserGroup> findItem(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        return null;
    }

    @Override
    @Transactional
    public List<UserGroup> findAll() {

        Session currentSession = sessionFactory.getCurrentSession();

        @SuppressWarnings("JpaQlInspection")
        Query<UserGroup> query = currentSession.createQuery("select g from UserGroup g");

        return query.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
