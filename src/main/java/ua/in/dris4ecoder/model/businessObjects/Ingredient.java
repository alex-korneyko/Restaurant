package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;

/**
 * Created by Alex Korneyko on 01.08.2016 20:14.
 */
@Entity
@Table(name = "service.ingredients")
public class Ingredient {

    @Id
    @Column(name = "id")
    private short id;

    @Transient
    private SimpleIntegerProperty idProp = new SimpleIntegerProperty();

    @Column(name = "ingredient_name")
    private String ingredientName;

    @Transient
    private SimpleStringProperty ingredientNameProp = new SimpleStringProperty();

    public Ingredient() {
    }

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
        this.ingredientNameProp.set(ingredientName);
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
        idProp.set(id);
    }

    public String getIngredientName() {
        return ingredientNameProp.get();
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
        ingredientNameProp.set(ingredientName);
    }

    public SimpleIntegerProperty idPropProperty() {
        idProp.set(this.id);
        return idProp;
    }

    public SimpleStringProperty ingredientNamePropProperty() {
        ingredientNameProp.set(this.ingredientName);
        return ingredientNameProp;
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
