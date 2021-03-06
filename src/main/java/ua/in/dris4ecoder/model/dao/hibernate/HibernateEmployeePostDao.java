package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alex Korneyko on 02.11.2016 21:30.
 */
public class HibernateEmployeePostDao implements RestaurantDao<EmployeePost> {

    SessionFactory sessionFactory;

    @Override
    @Transactional
    public int addItem(EmployeePost item) {

        EmployeePost post = findItem(item.getPostName());
        if (post != null) return post.getId();

        Session currentSession = sessionFactory.getCurrentSession();
        Serializable save = currentSession.save(item);
        return ((int) save);
    }

    @Override
    @Transactional
    public void removeItemById(int id) {

        Session currentSession = sessionFactory.getCurrentSession();
        @SuppressWarnings("JpaQlInspection")
        Query query = currentSession.createQuery("delete from EmployeePost ep where ep.id = :id");
        query.setParameter("id", id);
        int i = query.executeUpdate();
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
    public EmployeePost findItem(String name) {

        Session currentSession = sessionFactory.getCurrentSession();
        @SuppressWarnings("JpaQlInspection")
        Query<EmployeePost> query = currentSession.createQuery("select ep from EmployeePost ep where ep.postName = :name");
        query.setParameter("name", name);
        return query.uniqueResult();
    }

    @Override
    @Transactional
    public EmployeePost findItem(EmployeePost item) {

        return sessionFactory.getCurrentSession().find(EmployeePost.class, item.getId());
    }

    @Override
    @Transactional
    public List<EmployeePost> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    @Transactional
    public List<EmployeePost> findItem(LocalDateTime startPeriod, LocalDateTime endPeriod) {
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
