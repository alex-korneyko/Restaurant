package ua.in.dris4ecoder.model.dao;

import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessObjects.KitchenProcess;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alex Korneyko on 28.07.2016 20:14.
 */
public interface RestaurantDao<T> {

    void addItem(T item);

    void removeItemById(int id);

    void removeItemByName(String name);

    void editItem(int id, T changedItem);

    T findItemById(int id);

    List<T> findItem(String name);

    List<T> findItem(T employee);

    List<T> findItem(OrderDishStatus status);

    List<T> findItem(LocalDate startPeriod, LocalDate endPeriod);

    List<T> findAll();
}
