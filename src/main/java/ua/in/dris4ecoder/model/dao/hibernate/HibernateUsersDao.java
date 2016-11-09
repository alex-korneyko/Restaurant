package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.businessObjects.UserGroup;
import ua.in.dris4ecoder.model.businessObjects.UserImpl;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alex Korneyko on 27.10.2016 19:27.
 */
public class HibernateUsersDao implements RestaurantDao<UserImpl> {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public int addItem(UserImpl user) {

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(user);
        return 0;
    }

    @Override
    @Transactional
    public void removeItemById(int id) {

    }

    @Override
    @Transactional
    public void removeItemByName(String name) {

        Session currentSession = sessionFactory.getCurrentSession();
        @SuppressWarnings("JpaQlInspection")
        Query query = currentSession.createQuery("delete from UserImpl u where u.userLogin = :name");
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void removeItem(UserImpl item) {

    }

    @Override
    @Transactional
    public void editItem(int id, UserImpl changedItem) {

        sessionFactory.getCurrentSession().update(changedItem);
    }

    @Override
    @Transactional
    public UserImpl findItemById(int id) {
        return null;
    }

    @Override
    @Transactional
    public UserImpl findItem(String login) {

        Session currentSession = sessionFactory.getCurrentSession();

        @SuppressWarnings("JpaQlInspection")
        Query<UserImpl> query = currentSession.createQuery("select u from UserImpl u where u.userLogin = :login");
        query.setParameter("login", login);

        return query.uniqueResult();
    }

    @Override
    @Transactional
    public UserImpl findItem(UserImpl item) {
        return null;
    }

    @Override
    @Transactional
    public List<UserImpl> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    @Transactional
    public List<UserImpl> findItem(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        return null;
    }

    @Override
    @Transactional
    @SuppressWarnings("JpaQlInspection")
    public List<UserImpl> findAll() {

        Session currentSession = sessionFactory.getCurrentSession();
        Query<UserImpl> query = currentSession.createQuery("select u from UserImpl u");

        return query.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
