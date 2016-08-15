package ua.in.dris4ecoder.model.businessObjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Alex Korneyko on 01.08.2016 20:14.
 */
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @Column(name = "id")
    public short id;

    @Column(name = "ingredient_name")
    public String ingredientName;

    public Ingredient() {
    }

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
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
