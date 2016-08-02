package ua.in.dris4ecoder.model;

/**
 * Created by Alex Korneyko on 01.08.2016 20:14.
 */
public class Dish {

    private String dishName;
    private DishCategory dishCategory;
    private double price;
    private double weight;

    public Dish(String dishName, DishCategory dishCategory) {
        this.dishName = dishName;
        this.dishCategory = dishCategory;
    }

    public Dish(String dishName, DishCategory dishCategory, double price, double weight) {
        this.dishName = dishName;
        this.dishCategory = dishCategory;
        this.price = price;
        this.weight = weight;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public DishCategory getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
