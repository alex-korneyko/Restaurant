package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.Dish;
import ua.in.dris4ecoder.model.businessObjects.KitchenProcess;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public int addItem(Dish item) {
        Set<Dish> dishes = new HashSet<>(findAll());
        if(!dishes.contains(item)) {
            Serializable save = sessionFactory.getCurrentSession().save(item);
            return ((int) save);
        } else {
            throw new RuntimeException("Object already exist: " + item.toString());
        }
    }

    @Override
    public void removeItemById(int id) {

        removeItem(findItemById(id));
    }

    @Override
    public void removeItemByName(String name) {

        removeItem(findItem(name));
    }

    @Override
    public void removeItem(Dish item) {
        sessionFactory.getCurrentSession().remove(item);
    }

    @Override
    @Transactional
    public void editItem(int id, Dish changedItem) {

        final Session currentSession = sessionFactory.getCurrentSession();
//        changedItem.setId(id);
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
    public Dish findItem(String name) {

        final Session currentSession = sessionFactory.getCurrentSession();
        final Query<Dish> query = currentSession.createQuery("select d from Dish d where d.dishName = :name");
        query.setParameter("name", name);
        return query.uniqueResult();
    }

    @Override
    public Dish findItem(Dish item) {

        return sessionFactory.getCurrentSession().find(Dish.class, item.getId());
    }

    @Override
    public List<Dish> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<Dish> findItem(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        return null;
    }

    @Override
    public List<Dish> findAll() {
        final Query<Dish> query = sessionFactory.getCurrentSession().createQuery("select i from Dish i");
        final List<Dish> list = query.list();
        return list;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
