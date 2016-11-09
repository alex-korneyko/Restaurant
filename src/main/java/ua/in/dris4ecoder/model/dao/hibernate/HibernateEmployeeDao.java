package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alex Korneyko on 02.11.2016 21:31.
 */
public class HibernateEmployeeDao implements RestaurantDao<Employee> {

    SessionFactory sessionFactory;

    @Override
    @Transactional
    public int addItem(Employee item) {

        Session currentSession = sessionFactory.getCurrentSession();
        Serializable save = currentSession.save(item);
        return ((int) save);
    }

    @Override
    @Transactional
    public void removeItemById(int id) {

        Session currentSession = sessionFactory.getCurrentSession();
        @SuppressWarnings("JpaQlInspection")
        Query query = currentSession.createQuery("delete from Employee e where e.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void removeItemByName(String name) {

    }

    @Override
    @Transactional
    public void removeItem(Employee item) {

        sessionFactory.getCurrentSession().delete(item);
    }

    @Override
    @Transactional
    public void editItem(int id, Employee changedItem) {

        sessionFactory.getCurrentSession().update(changedItem);
    }

    @Override
    @Transactional
    public Employee findItemById(int id) {

        return sessionFactory.getCurrentSession().find(Employee.class, id);
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    @Transactional
    public Employee findItem(String name) {

        Session currentSession = sessionFactory.getCurrentSession();
        Query<Employee> query = currentSession.createQuery("select e from Employee e where e.user.userLogin = :name");
        query.setParameter("name", name);
        return query.uniqueResult();
    }

    @Override
    @Transactional
    public Employee findItem(Employee item) {

        return sessionFactory.getCurrentSession().find(Employee.class, item.getId());
    }

    @Override
    @Transactional
    public List<Employee> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    @Transactional
    public List<Employee> findItem(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        return null;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {

        Session currentSession = sessionFactory.getCurrentSession();
        @SuppressWarnings("JpaQlInspection")
        Query<Employee> query = currentSession.createQuery("select e from Employee e");

        return query.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
