package ua.in.dris4ecoder.model.businessObjects;

/**
 * Created by Alex Korneyko on 01.08.2016 20:14.
 */
public class Ingredient {

    private String ingredientName;

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
