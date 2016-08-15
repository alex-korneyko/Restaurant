package ua.in.dris4ecoder.model.dao.hibernate;

import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.model.businessObjects.KitchenProcess;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alex Korneyko on 15.08.2016 10:55.
 */
public class HibernateIngredientDao implements RestaurantDao<Ingredient> {

    @Override
    public void addItem(Ingredient item) {

    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void editItem(int id, Ingredient changedItem) {

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
    public List<Ingredient> findItem(Ingredient employee) {
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
}
