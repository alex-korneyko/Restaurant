package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.model.businessObjects.KitchenProcess;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alex Korneyko on 15.08.2016 10:55.
 */
//@SuppressWarnings("JpaQlInspection")
public class HibernateIngredientDao implements RestaurantDao<Ingredient> {

    private SessionFactory sessionFactory;

    @Override
    public void addItem(Ingredient item) {
        final Query<Ingredient> query = sessionFactory.getCurrentSession().createQuery("select i from Ingredient i");
        Set<Ingredient> ingredients = new HashSet<>(query.list());
        if (!ingredients.contains(item)) {
            sessionFactory.getCurrentSession().save(item);
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
        Ingredient ingredient;
        final Session session = sessionFactory.getCurrentSession();

        final Query<Ingredient> findQuery = session.createQuery("select i from Ingredient i where i.id = :id");
        findQuery.setParameter("id", id);
        ingredient = findQuery.uniqueResult();
        if(ingredient == null) {
            throw new ObjectNotFoundException(id, "Ingredient not found");
        }

        final Query query = session.createQuery("update Ingredient i set i.ingredientName = :newName where i.ingredientName = :oldName");
        query.setParameter("newName", changedItem.getIngredientName());
        query.setParameter("oldName", ingredient.getIngredientName());
        query.executeUpdate();
    }

    @Override
    public Ingredient findItemById(int id) {
        return null;
    }

    @Override
    public List<Ingredient> findItem(String name) {
        return null;
    }

    @Override
    public List<Ingredient> findItem(Ingredient item) {
        return null;
    }

    @Override
    public KitchenProcess findItem(int orderId) {
        return null;
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
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
