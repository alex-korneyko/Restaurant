package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by Alex Korneyko on 06.09.2016 18:18.
 */
@Entity
@Table(name = "service.warehouse")
public class WarehousePosition implements BusinessObject {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @OneToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "ingredient_amount")
    private double ingredientAmount;

    @Transient
    private SimpleIntegerProperty idProp = new SimpleIntegerProperty();

    @Transient
    private SimpleStringProperty ingredientNameProp = new SimpleStringProperty();

    @Transient
    private SimpleDoubleProperty ingredientWeightProp = new SimpleDoubleProperty();

    @Transient
    private SimpleStringProperty ingredientUnitProp = new SimpleStringProperty();

    public WarehousePosition() {
    }

    public WarehousePosition(Ingredient ingredient) {
        this.ingredient = ingredient;
        this.ingredientAmount = ingredient.getIngredientWeight();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        ingredientNameProp.set(ingredient.getIngredientName());
        ingredientWeightProp.set(ingredient.getIngredientWeight());
        this.ingredient = ingredient;
    }

    public double getIngredientAmount() {
        return ingredientAmount;
    }

    public BigDecimal getIngredientAmountInBigDec() {

        return (new BigDecimal(ingredientAmount)).setScale(4, BigDecimal.ROUND_HALF_UP);
    }

    public void setIngredientAmount(double ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    public SimpleIntegerProperty idPropProperty() {
        idProp.set(id);
        return idProp;
    }

    public SimpleStringProperty ingredientNamePropProperty() {
        ingredientNameProp.set(ingredient.getIngredientName());
        return ingredientNameProp;
    }

    public SimpleDoubleProperty ingredientWeightPropProperty() {
        ingredientWeightProp.set(ingredientAmount);
        return ingredientWeightProp;
    }

    public SimpleStringProperty ingredientUnitPropProperty() {
        ingredientUnitProp.set(ingredient.getUnit().getUnitName());
        return ingredientUnitProp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarehousePosition)) return false;

        WarehousePosition that = (WarehousePosition) o;

        return ingredient != null ? ingredient.equals(that.ingredient) : that.ingredient == null;

    }

    @Override
    public int hashCode() {
        return ingredient != null ? ingredient.hashCode() : 0;
    }
}
