package ua.in.dris4ecoder.model.dao;

import ua.in.dris4ecoder.model.businessObjects.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alex Korneyko on 28.07.2016 20:14.
 */
public interface RestaurantDao<T> {

    int addItem(T item);

    void removeItemById(int id);

    void removeItemByName(String name);

    void removeItem(T item);

    void editItem(int id, T changedItem);

    T findItemById(int id);

    T findItem(String name);

    T findItem(T item);

    List<T> findItem(OrderDishStatus status);

    List<T> findItem(LocalDateTime startPeriod, LocalDateTime endPeriod);

    List<T> findAll();
}
