package ua.in.dris4ecoder.model.businessObjects;

/**
 * Created by Alex Korneyko on 01.08.2016 20:15.
 */
public enum  DishCategory {

    FIRST_COURSE, MAIN_COURSE, APERITIF, APPETIZER, COLD_PLATTER, SALAD, DESSERT,
    SOFT_DRINK, LOW_ALCOHOL, HARD_DRINK, CARBONATED_DRINK, STILL_DRINK, COCKTAIL;

    // TODO: 17.08.2016 Method should be realized
    @Override
    public String toString() {
        switch (this) {
            case FIRST_COURSE: return "";
            case MAIN_COURSE: return "Второе";
            case APERITIF: return "Аперитив";
            case APPETIZER: return "Закуска";
            
            default: throw new IllegalArgumentException();
        }
    }
}
