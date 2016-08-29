package ua.in.dris4ecoder.model.businessObjects;

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

    @Transient
    private SimpleIntegerProperty idProp = new SimpleIntegerProperty();
    @Transient
    private SimpleStringProperty ingredientNameProp = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty unitNameProperty = new SimpleStringProperty();

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

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", ingredientName='" + ingredientName + '\'' +
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
