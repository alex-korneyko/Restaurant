package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alex Korneyko on 02.11.2016 21:30.
 */
public class HibernateEmployeePostDao implements RestaurantDao<EmployeePost> {

    SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addItem(EmployeePost item) {

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(item);
    }

    @Override
    @Transactional
    public void removeItemById(int id) {

        Session currentSession = sessionFactory.getCurrentSession();
        @SuppressWarnings("JpaQlInspection")
        Query query = currentSession.createQuery("delete from EmployeePost ep where ep.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void removeItemByName(String name) {

        Session currentSession = sessionFactory.getCurrentSession();
        @SuppressWarnings("JpaQlInspection")
        Query query = currentSession.createQuery("delete from EmployeePost ep where ep.postName = :name");
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void removeItem(EmployeePost item) {

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(item);
    }

    @Override
    @Transactional
    public void editItem(int id, EmployeePost changedItem) {

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(changedItem);
    }

    @Override
    @Transactional
    public EmployeePost findItemById(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        return currentSession.find(EmployeePost.class, id);
    }

    @Override
    @Transactional
    public List<EmployeePost> findItem(String name) {

        Session currentSession = sessionFactory.getCurrentSession();
        @SuppressWarnings("JpaQlInspection")
        Query<EmployeePost> query = currentSession.createQuery("select ep from EmployeePost ep where ep.postName like :name");
        query.setParameter("name", name);
        return query.list();
    }

    @Override
    @Transactional
    public List<EmployeePost> findItem(EmployeePost item) {

        Session currentSession = sessionFactory.getCurrentSession();

        return Collections.singletonList(currentSession.find(EmployeePost.class, item.getId()));
    }

    @Override
    @Transactional
    public List<EmployeePost> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    @Transactional
    public List<EmployeePost> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @Override
    @Transactional
    public List<EmployeePost> findAll() {

        Session currentSession = sessionFactory.getCurrentSession();
        @SuppressWarnings("JpaQlInspection")
        Query<EmployeePost> query = currentSession.createQuery("select ep from EmployeePost ep");

        return query.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
