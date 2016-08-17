package ua.in.dris4ecoder.model.businessObjects;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Alex Korneyko on 01.08.2016 20:14.
 */
@Entity
@Table(name = "service.dishes")
public class Dish {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Column(name = "dish_name")
    private String dishName;

    @Column(name = "dish_category")
    @Enumerated(EnumType.STRING)
    private DishCategory dishCategory;

    @Column(name = "price")
    private double price;

    @Column(name = "weight")
    private double weight;

    @ManyToMany
    @JoinTable(
            name = "service.dish_composition",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    public Dish() {
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", dishName='" + dishName + '\'' +
                ", dishCategory=" + dishCategory +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;

        Dish dish = (Dish) o;

        if (Double.compare(dish.price, price) != 0) return false;
        if (Double.compare(dish.weight, weight) != 0) return false;
        if (dishName != null ? !dishName.equals(dish.dishName) : dish.dishName != null) return false;
        return dishCategory == dish.dishCategory;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = dishName != null ? dishName.hashCode() : 0;
        result = 31 * result + (dishCategory != null ? dishCategory.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
