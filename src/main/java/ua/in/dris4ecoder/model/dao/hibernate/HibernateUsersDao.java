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
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void removeItem(User item) {

    }

    @Override
    @Transactional
    public void editItem(int id, User changedItem) {

        sessionFactory.getCurrentSession().update(changedItem);
    }

    @Override
    public User findItemById(int id) {
        return null;
    }

    @Override
    @Transactional
    public List<User> findItem(String login) {

        Session currentSession = sessionFactory.getCurrentSession();

        @SuppressWarnings("JpaQlInspection")
        Query<User> query = currentSession.createQuery("select u from UserImpl u where u.userLogin = :login");
        query.setParameter("login", login);

        return query.list();
    }

    @Override
    public List<User> findItem(User item) {
        return null;
    }

    @Override
    public List<User> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<User> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
