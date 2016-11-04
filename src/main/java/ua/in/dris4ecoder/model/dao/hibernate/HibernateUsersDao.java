package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.businessObjects.User;
import ua.in.dris4ecoder.model.businessObjects.UserGroup;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alex Korneyko on 27.10.2016 19:27.
 */
public class HibernateUsersDao implements RestaurantDao<User> {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addItem(User user) {

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(user);
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
    public void removeItem(User item) {

    }

    @Override
    @Transactional
    public void editItem(int id, User changedItem) {

        sessionFactory.getCurrentSession().update(changedItem);
    }

    @Override
    @Transactional
    public User findItemById(int id) {
        return null;
    }

    @Override
    @Transactional
    public User findItem(String login) {

        Session currentSession = sessionFactory.getCurrentSession();

        @SuppressWarnings("JpaQlInspection")
        Query<User> query = currentSession.createQuery("select u from UserImpl u where u.userLogin = :login");
        query.setParameter("login", login);

        return query.uniqueResult();
    }

    @Override
    @Transactional
    public User findItem(User item) {
        return null;
    }

    @Override
    @Transactional
    public List<User> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    @Transactional
    public List<User> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
