package ua.in.dris4ecoder.model.businessObjects;

/**
 * Created by Alex Korneyko on 01.08.2016 20:15.
 */
public enum  DishCategory {

   MAIN_CORSE, FIRST_CORSE, APERATIF, APPETIZER, COLD_PLATTER, SALAD, DESSERT,
    SOFT_DRINK, LOW_ALCOHOL, HARD_DRINK, CARBONATED_DRINK, STILL_DRINK, COCKTAIL;

    // TODO: 17.08.2016 Method should be realized
    @Override
    public String toString() {
        switch (this) {
            case MAIN_CORSE: return "Первое";
            
            default: throw new IllegalArgumentException();
        }
    }
}
