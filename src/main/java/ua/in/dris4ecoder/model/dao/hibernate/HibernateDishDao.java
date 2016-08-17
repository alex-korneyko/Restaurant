package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.in.dris4ecoder.model.businessObjects.Dish;
import ua.in.dris4ecoder.model.businessObjects.KitchenProcess;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 15.08.2016 10:56.
 */
@SuppressWarnings("JpaQlInspection")
public class HibernateDishDao implements RestaurantDao<Dish> {

    private SessionFactory sessionFactory;

    @Override
    public void addItem(Dish item) {
        Set<Dish> dishes = new HashSet<>(findAll());
        if(!dishes.contains(item)) {
            sessionFactory.getCurrentSession().save(item);
        } else {
            throw new RuntimeException("Object already exist: " + item.toString());
        }
    }

    @Override
    public void removeItemById(int id) {

        final Session currentSession = sessionFactory.getCurrentSession();
        final Query query = currentSession.createQuery("delete from Dish d where d.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void removeItemByName(String name) {

        final Session currentSession = sessionFactory.getCurrentSession();
        final Query query = currentSession.createQuery("delete from Dish d where d.dishName like :name");
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    public void editItem(int id, Dish changedItem) {

        final Session currentSession = sessionFactory.getCurrentSession();
        changedItem.setId(id);
        currentSession.update(changedItem);
    }

    @Override
    public Dish findItemById(int id) {

        final Session currentSession = sessionFactory.getCurrentSession();
        final Query<Dish> query = currentSession.createQuery("select d from Dish d where d.id = :id");
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    @Override
    public List<Dish> findItem(String name) {

        final Session currentSession = sessionFactory.getCurrentSession();
        final Query<Dish> query = currentSession.createQuery("select d from Dish d where d.dishName like :name");
        query.setParameter("name", name);
        return query.list();
    }

    @Override
    public List<Dish> findItem(Dish item) {
        return findAll().stream().filter(dish -> dish.equals(item)).collect(Collectors.toList());
    }

    @Override
    public List<Dish> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<Dish> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @Override
    public List<Dish> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select i from Dish i").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
