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
public interface RestaurantDao {

    void addItem(Object item);

    void removeItem(int id);

    void editItem(int id, Object changedItem);

    Object findItem(String name);

    List<?> findItem(Employee employee);

    KitchenProcess findItem(int orderId);

    List<?> findItem(OrderDishStatus status);

    List<?> findItem(Date startPeriod, Date endPeriod);

    List<?> findAll();
}
