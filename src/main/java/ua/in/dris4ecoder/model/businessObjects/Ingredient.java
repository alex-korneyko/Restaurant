package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Alex Korneyko on 01.08.2016 20:14.
 */
@Entity
@Table(name = "service.ingredients")
public class Ingredient {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Column(name = "ingredient_name")
    private String ingredientName;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(name = "ingredient_price")
    private Double ingredientPrice = 0.0;

    @Column(name = "ingredient_weight")
    private Double ingredientWeight = 0.0;

    @Transient
    private Double ingredientPriceOfWeight = ingredientWeight * ingredientPrice;

    @Transient
    private SimpleIntegerProperty idProp = new SimpleIntegerProperty();
    @Transient
    private SimpleStringProperty ingredientNameProp = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty unitNameProperty = new SimpleStringProperty();
    @Transient
    private SimpleDoubleProperty ingredientPriceProp = new SimpleDoubleProperty();
    @Transient
    private SimpleDoubleProperty ingredientWeightProp = new SimpleDoubleProperty();
    @Transient
    private SimpleDoubleProperty ingredientPriceOfWeightProp = new SimpleDoubleProperty();

    public Ingredient() {
    }

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
        this.ingredientNameProp.set(ingredientName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        idProp.set(id);
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
        ingredientNameProp.set(ingredientName);
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Double getIngredientPrice() {
        return ingredientPrice;
    }

    public void setIngredientPrice(double ingredientPrice) {
        ingredientPriceProp.set(ingredientPrice);
        ingredientPriceOfWeight = ingredientWeight * ingredientPrice;
        this.ingredientPrice = ingredientPrice;
    }

    public Double getIngredientWeight() {
        return ingredientWeight;
    }

    public void setIngredientWeight(double ingredientWeight) {
        ingredientWeightProp.set(ingredientWeight);
        ingredientPriceOfWeight = ingredientWeight * ingredientPrice;
        this.ingredientWeight = ingredientWeight;
    }

    public Double getIngredientPriceOfWeight() {
//        ingredientPriceOfWeight = ingredientWeight * ingredientWeight;
        return ingredientPriceOfWeight;
    }

    public SimpleIntegerProperty idPropProperty() {
        idProp.set(this.id);
        return idProp;
    }

    public SimpleStringProperty ingredientNamePropProperty() {
        ingredientNameProp.set(this.ingredientName);
        return ingredientNameProp;
    }

    public SimpleStringProperty unitNamePropertyProperty() {
        unitNameProperty.set(unit.getUnitName());
        return unitNameProperty;
    }

    public SimpleDoubleProperty ingredientPricePropProperty() {
        ingredientPriceProp.set(ingredientPrice);
        return ingredientPriceProp;
    }

    public SimpleDoubleProperty ingredientWeightPropProperty() {
        ingredientWeightProp.set(ingredientWeight);
        return ingredientWeightProp;
    }

    public SimpleDoubleProperty ingredientPriceOfWeightPropProperty() {
        ingredientPriceOfWeightProp.set(getIngredientPriceOfWeight());
        return ingredientPriceOfWeightProp;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", ingredientName='" + ingredientName + '\'' +
                ", unit=" + unit +
                ", ingredientPrice=" + ingredientPrice +
                ", ingredientWeight=" + ingredientWeight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;

        Ingredient that = (Ingredient) o;

        return ingredientName != null ? ingredientName.equals(that.ingredientName) : that.ingredientName == null;

    }

    @Override
    public int hashCode() {
        return ingredientName != null ? ingredientName.hashCode() : 0;
    }
}
