package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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

    @Transient
    private SimpleIntegerProperty idProp = new SimpleIntegerProperty();

    @Transient
    private SimpleStringProperty dishNameProp = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty dishCategoryProp = new SimpleStringProperty();

    @Transient
    private SimpleDoubleProperty priceProp = new SimpleDoubleProperty();

    @Transient
    private SimpleDoubleProperty weightProp = new SimpleDoubleProperty();

    public Dish() {
    }

    public Dish(String dishName, DishCategory dishCategory) {
        this.dishName = dishName;
        this.dishNameProp.set(dishName);
        this.dishCategory = dishCategory;
        this.dishCategoryProp.set(dishCategory.toString());
    }

    public Dish(String dishName, DishCategory dishCategory, double price, double weight) {
        this(dishName, dishCategory);
        this.price = price;
        this.priceProp.setValue(price);
        this.weight = weight;
        this.weightProp.setValue(weight);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.idProp.set(id);
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
        this.dishNameProp.set(dishName);
    }

    public DishCategory getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
        this.dishCategoryProp.set(dishCategory.toString());
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.priceProp.setValue(price);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        this.weightProp.setValue(weight);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


    public SimpleIntegerProperty idPropProperty() {
        idProp.set(id);
        return idProp;
    }

    public SimpleStringProperty dishNamePropProperty() {
        dishNameProp.set(this.dishName);
        return dishNameProp;
    }

    public SimpleStringProperty dishCategoryPropProperty() {
        dishCategoryProp.set(dishCategory.toString());
        return dishCategoryProp;
    }

    public SimpleDoubleProperty pricePropProperty() {
        priceProp.setValue(price);
        return priceProp;
    }

    public SimpleDoubleProperty weightPropProperty() {
        weightProp.setValue(weight);
        return weightProp;
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
