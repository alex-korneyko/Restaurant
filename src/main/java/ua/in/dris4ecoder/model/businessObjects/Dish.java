package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Column(name = "auto_price")
    private boolean autoPrice;

    @Column(name = "weight")
    private double weight;

    @Column(name = "auto_weight")
    private boolean autoWeight;

    @ElementCollection
    @CollectionTable(name = "service.dish_ingredients_weights")
    @Column(name = "ingredient_weight")
    @MapKeyJoinColumn(name = "ingredient_id")
    @Fetch(FetchMode.JOIN)
    private Map<Ingredient, Double> ingredientWeightPerDish = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "service.dish_ingredients_prices")
    @Column(name = "ingredient_price")
    @MapKeyJoinColumn(name = "ingredient_id")
    @Fetch(FetchMode.JOIN)
    private Map<Ingredient, Double> ingredientCostPerDish = new HashMap<>();

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

    public Dish(boolean autoPrice, boolean autoWeight) {
        this.autoPrice = autoPrice;
        this.autoWeight = autoWeight;
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

    public String getDishNameWithHtmlQuot() {

        if (dishName == null) {
            return "";
        }

        StringBuilder nameWithBackSlash = new StringBuilder();

        for (char c : dishName.toCharArray()) {
            if (c =='"') {
                nameWithBackSlash.append("&quot;");
                continue;
            }
            nameWithBackSlash.append(c);
        }

        return nameWithBackSlash.toString();
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

    public BigDecimal getPrice() {

        if (autoPrice) {
            price = 0;
            for (Ingredient ingredient: ingredientCostPerDish.keySet()) {
                price += (ingredientCostPerDish.get(ingredient) * ingredientWeightPerDish.get(ingredient));
            }
        }

        return (new BigDecimal(price)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setPrice(double price) {
        this.price = price;
        this.priceProp.setValue(price);
    }

    public boolean isAutoPrice() {
        return autoPrice;
    }

    public void setAutoPrice(boolean autoPrice) {
        this.autoPrice = autoPrice;
    }

    public BigDecimal getWeight() {

        if (autoWeight) {
            weight = ingredientWeightPerDish.keySet().stream().mapToDouble(key -> ingredientWeightPerDish.get(key)).sum();
        }

        return new BigDecimal(weight, new MathContext(3));
    }

    public void setWeight(double weight) {
        this.weight = weight;
        this.weightProp.setValue(weight);
    }

    public boolean isAutoWeight() {
        return autoWeight;
    }

    public void setAutoWeight(boolean autoWeight) {
        this.autoWeight = autoWeight;
    }

    public List<Ingredient> getIngredients() {

        ArrayList<Ingredient> ingredients = new ArrayList<>(ingredientCostPerDish.keySet());

        for (Ingredient ingredient : ingredients) {
            ingredient.setIngredientPrice(ingredientCostPerDish.get(ingredient));
            ingredient.setIngredientWeight(ingredientWeightPerDish.get(ingredient));
        }
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {

        ingredientWeightPerDish.clear();
        ingredientCostPerDish.clear();
        ingredients.forEach(ingredient -> {
            ingredientWeightPerDish.put(ingredient, ingredient.getIngredientWeight());
            ingredientCostPerDish.put(ingredient, ingredient.getIngredientPrice());
        });
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

    public void addIngredient(Ingredient ingredient) {

        removeIngredient(ingredient);

        ingredientCostPerDish.put(ingredient, ingredient.getIngredientPrice());
        ingredientWeightPerDish.put(ingredient, ingredient.getIngredientWeight());
    }

    public void removeIngredient(Ingredient ingredient) {

//        ingredients.remove(ingredient);
        ingredientWeightPerDish.remove(ingredient);
        ingredientCostPerDish.remove(ingredient);
    }

    public Ingredient getIngredientWithParamsInDish(Ingredient ingredient) {

        ingredient.setIngredientPrice(ingredientCostPerDish.get(ingredient));
        ingredient.setIngredientWeight(ingredientWeightPerDish.get(ingredient));

        return ingredient;
    }

    public void removeAllIngredients() {

        ingredientWeightPerDish.clear();
        ingredientCostPerDish.clear();
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

        if (!dishName.equals(dish.dishName)) return false;
        return dishCategory == dish.dishCategory;

    }

    @Override
    public int hashCode() {
        int result = dishName.hashCode();
        result = 31 * result + dishCategory.hashCode();
        return result;
    }
}
