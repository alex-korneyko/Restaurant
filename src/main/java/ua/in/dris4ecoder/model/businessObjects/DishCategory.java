package ua.in.dris4ecoder.model.businessObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Korneyko on 01.08.2016 20:15.
 */
public enum  DishCategory {

    FIRST_COURSE, MAIN_COURSE, APERITIF, APPETIZER, COLD_PLATTER, SALAD, DESSERT,
    SOFT_DRINK, LOW_ALCOHOL, HARD_DRINK, CARBONATED_DRINK, STILL_DRINK, COCKTAIL;

    @Override
    public String toString() {
        switch (this) {
            case FIRST_COURSE: return "Первое";
            case MAIN_COURSE: return "Второе";
            case APERITIF: return "Аперитив";
            case APPETIZER: return "Закуска";
            case COLD_PLATTER: return "Холодная закуска";
            case SALAD: return "Салат";
            case DESSERT: return "Десерт";
            case SOFT_DRINK: return "б/а напиток";
            case LOW_ALCOHOL: return "с/а напиток";
            case HARD_DRINK: return "Крепкий напиток";
            case CARBONATED_DRINK: return "Газировка";
            case STILL_DRINK: return "Сок";
            case COCKTAIL: return "Коктейль";
            default: throw new IllegalArgumentException();
        }
    }

    public static List<String> stringValues() {
        List<String> list = new ArrayList<>();
        for (DishCategory dishCategory: values()) {
            list.add(dishCategory.toString());
        }
        return list;
    }

    public static DishCategory getValueByStringName(String value) {

        switch (value) {
            case "Первое": return FIRST_COURSE;
            case "Второе": return MAIN_COURSE;
            case "Аперитив": return APERITIF;
            case "Закуска": return APPETIZER;
            case "Холодная закуска": return COLD_PLATTER;
            case "Салат": return SALAD;
            case "Десерт": return DESSERT;
            case "б/а напиток": return SOFT_DRINK;
            case "с/а напиток": return LOW_ALCOHOL;
            case "Крепкий напиток": return HARD_DRINK;
            case "Газировка": return CARBONATED_DRINK;
            case "Сок": return STILL_DRINK;
            case "Коктейль": return COCKTAIL;
            default: throw new IllegalArgumentException(value);
        }
    }
}
