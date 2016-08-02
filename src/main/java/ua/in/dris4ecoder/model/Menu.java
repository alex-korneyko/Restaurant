package ua.in.dris4ecoder.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Korneyko on 01.08.2016 20:15.
 */
public class Menu {

    private String name;

    private List<Dish> dishList;

    public Menu(String name) {
        this.name = name;
        dishList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getDishList() {
        return dishList;
    }
}
