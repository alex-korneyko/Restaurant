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
 * Created by Alex Korneyko on 01.08.2016 20:14.
 */
@Entity
@Table(name = "service.ingredients")
public class Ingredient implements Cloneable {

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

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

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
    private SimpleStringProperty currencyShortNameProp = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty currencyLongNameProp = new SimpleStringProperty();
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        currencyShortNameProp.set(currency.getShortName());
        currencyLongNameProp.set(currency.getLongName());
        this.currency = currency;
    }

    public Double getIngredientPrice() {
        return ingredientPrice;
    }

    public BigDecimal getIngredientPriceInBDecimal() {

        return (new BigDecimal(ingredientPrice)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setIngredientPrice(double ingredientPrice) {
        ingredientPriceProp.set(ingredientPrice);
        ingredientPriceOfWeight = ingredientWeight * ingredientPrice;
        this.ingredientPrice = ingredientPrice;
    }

    public Double getIngredientWeight() {
        return ingredientWeight;
    }

    public BigDecimal getIngredientWeightInBDecimal() {

        return (new BigDecimal(ingredientWeight)).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public void setIngredientWeight(double ingredientWeight) {
        ingredientWeightProp.set(ingredientWeight);
        ingredientPriceOfWeight = ingredientWeight * ingredientPrice;
        this.ingredientWeight = ingredientWeight;
    }

    public BigDecimal getIngredientPriceOfWeight() {
//        ingredientPriceOfWeight = ingredientWeight * ingredientWeight;
        return (new BigDecimal(ingredientPriceOfWeight)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public String getNameWithHtmlQuot() {

        StringBuilder nameWithBackSlash = new StringBuilder();

        for (char c : ingredientName.toCharArray()) {
            if (c =='"') {
                nameWithBackSlash.append("&quot;");
                continue;
            }
            nameWithBackSlash.append(c);
        }

        return nameWithBackSlash.toString();
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

    public SimpleStringProperty currencyShortNamePropProperty() {
        currencyShortNameProp.set(currency.getShortName());
        return currencyShortNameProp;
    }

    public SimpleStringProperty currencyLongNamePropProperty() {
        currencyLongNameProp.set(currency.getLongName());
        return currencyLongNameProp;
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
        ingredientPriceOfWeightProp.set(getIngredientPriceOfWeight().doubleValue());
        return ingredientPriceOfWeightProp;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", ingredientName='" + ingredientName + '\'' +
                ", unit=" + unit +
                ", ingredientPrice=" + ingredientPrice +
                " " + currency.getShortName() +
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

    @Override
    public Ingredient clone(){

        Ingredient newIngredient = new Ingredient(this.ingredientName);
        newIngredient.setIngredientWeight(this.getIngredientWeight());
        newIngredient.setIngredientPrice(this.ingredientPrice);
        newIngredient.setId(this.getId());
        newIngredient.setUnit(this.unit);
        return newIngredient;
    }
}
