package ua.in.dris4ecoder.model.dao;

import ua.in.dris4ecoder.model.Employee;
import ua.in.dris4ecoder.model.EmployeePost;
import ua.in.dris4ecoder.model.KitchenProcess;
import ua.in.dris4ecoder.model.OrderDishStatus;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Alex Korneyko on 28.07.2016 20:14.
 */
public interface RestaurantDao<T> {

    void addItem(T item);

    void removeItemById(int id);

    void removeItemByName(String name);

    void editItem(int id, T changedItem);

    T findItemById(int id);

    T findItem(String name);

    List<T> findItem(Employee employee);

    KitchenProcess findItem(int orderId);

    List<T> findItem(OrderDishStatus status);

    List<T> findItem(Date startPeriod, Date endPeriod);

    List<T> findAll();
}
