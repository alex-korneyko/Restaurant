package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.model.businessObjects.KitchenProcess;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 15.08.2016 10:55.
 */
@SuppressWarnings("JpaQlInspection")
public class HibernateIngredientDao implements RestaurantDao<Ingredient> {

    private SessionFactory sessionFactory;

    @Override
    public void addItem(Ingredient item) {
        Set<Ingredient> ingredients = new HashSet<>(findAll());
        if (!ingredients.contains(item)) {
            sessionFactory.getCurrentSession().save(item);
        } else {
            throw new RuntimeException("Object already exist: " + item.toString());
        }
    }

    @Override
    public void removeItemById(int id) {

        final Session session = sessionFactory.getCurrentSession();
        final Query query = session.createQuery("delete from Ingredient i where i.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void removeItemByName(String name) {
        final Session session = sessionFactory.getCurrentSession();
        final Query query = session.createQuery("delete from Ingredient i where i.ingredientName = :name");
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    public void editItem(int id, Ingredient changedItem) {
        final Session session = sessionFactory.getCurrentSession();
        changedItem.setId(id);
        session.update(changedItem);
    }

    @Override
    public Ingredient findItemById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        final Query<Ingredient> query = session.createQuery("select i from Ingredient i where i.id = :id");
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    @Override
    public List<Ingredient> findItem(String name) {
        final Session session = sessionFactory.getCurrentSession();
        final Query<Ingredient> query = session.createQuery("select i from Ingredient i where i.ingredientName like :name");
        query.setParameter("name", name);
        return query.list();

    }

    @Override
    public List<Ingredient> findItem(Ingredient item) {
        List<Ingredient> ingredients = findAll();
        return ingredients.stream().filter(ingredient -> ingredient.equals(item)).collect(Collectors.toList());
    }

    @Override
    public List<Ingredient> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<Ingredient> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @Override
    public List<Ingredient> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select i from Ingredient i").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
