package ua.in.dris4ecoder.model.businessObjects;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Korneyko on 01.08.2016 20:15.
 */
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @Column(name = "id")
    private short id;

    @Column(name = "menu_name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "menu_composition",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishList;

    public Menu() {
        dishList = new ArrayList<>();
    }

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

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishList contain=" + dishList.size() +
                " dishes}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        return name != null ? name.equals(menu.name) : menu.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
