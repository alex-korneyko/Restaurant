package ua.in.dris4ecoder.controllers.businessControllers;

import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.Dish;
import ua.in.dris4ecoder.model.businessObjects.DishCategory;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.model.businessObjects.Menu;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.util.List;

/**
 * Created by Alex Korneyko on 15.08.2016 14:35.
 */
public class ManagementController implements BusinessController {

    private RestaurantDao<Ingredient> ingredientRestaurantDao;
    private RestaurantDao<Dish> dishRestaurantDao;
    private RestaurantDao<Menu> menuRestaurantDao;

    @Transactional
    public void addIngredient(String name) {
        ingredientRestaurantDao.addItem(new Ingredient(name));
    }

    @Transactional
    public void removeIngredient(String name) {
        ingredientRestaurantDao.removeItemByName(name);
    }

    @Transactional
    public void removeIngredient(int id) {
        ingredientRestaurantDao.removeItemById(id);
    }

    @Transactional
    public void editIngredient(int id, Ingredient newIngredient) {
        ingredientRestaurantDao.editItem(id, newIngredient);
    }

    @Transactional
    public Ingredient findIngredient(int id) {
        return ingredientRestaurantDao.findItemById(id);
    }

    @Transactional
    public List<Ingredient> findIngredient(String name) {
        return ingredientRestaurantDao.findItem(name);
    }

    @Transactional
    public List<Ingredient> findIngredient(Ingredient ingredient) {
        return ingredientRestaurantDao.findItem(ingredient);
    }

    @Transactional
    public List<Ingredient> findAllIngredients() {
        return ingredientRestaurantDao.findAll();
    }



    @Transactional
    public void addDish(String name, DishCategory dishCategory, List<Ingredient> ingredients) {
        final Dish dish = new Dish(name, dishCategory);
        dish.setIngredients(ingredients);
        dishRestaurantDao.addItem(dish);
    }

    @Transactional
    public void removeDish(String name) {
        dishRestaurantDao.removeItemByName(name);
    }

    @Transactional
    public void removeDish(int id) {
        dishRestaurantDao.removeItemById(id);
    }

    @Transactional
    public void editDish(int id, Dish newDish) {
        dishRestaurantDao.editItem(id, newDish);
    }

    @Transactional
    public Dish findDish(int id) {
        return dishRestaurantDao.findItemById(id);
    }

    @Transactional
    public List<Dish> findDish(String name) {
        return dishRestaurantDao.findItem(name);
    }

    @Transactional
    public List<Dish> findDish(Dish dish) {
        return dishRestaurantDao.findItem(dish);
    }

    @Transactional
    public List<Dish> findAllDishes() {
        return dishRestaurantDao.findAll();
    }

    //Setters
    public void setIngredientRestaurantDao(RestaurantDao<Ingredient> ingredientRestaurantDao) {
        this.ingredientRestaurantDao = ingredientRestaurantDao;
    }

    public void setDishRestaurantDao(RestaurantDao<Dish> dishRestaurantDao) {
        this.dishRestaurantDao = dishRestaurantDao;
    }

    public void setMenuRestaurantDao(RestaurantDao<Menu> menuRestaurantDao) {
        this.menuRestaurantDao = menuRestaurantDao;
    }
    //end Setters
}
