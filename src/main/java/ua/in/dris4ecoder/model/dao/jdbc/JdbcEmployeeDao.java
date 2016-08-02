package ua.in.dris4ecoder.model.dao.jdbc;

import ua.in.dris4ecoder.model.Employee;
import ua.in.dris4ecoder.model.KitchenProcess;
import ua.in.dris4ecoder.model.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex Korneyko on 02.08.2016 12:28.
 */
public class JdbcEmployeeDao implements RestaurantDao {
    @Override
    public void addItem(Object item) {

    }

    @Override
    public void removeItem(int id) {

    }

    @Override
    public void editItem(int id, Object changedItem) {

    }

    @Override
    public Object findItem(String name) {
        return null;
    }

    @Override
    public List<?> findItem(Employee employee) {
        return null;
    }

    @Override
    public KitchenProcess findItem(int orderId) {
        return null;
    }

    @Override
    public List<?> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<?> findItem(Date startPeriod, Date endPeriod) {
        return null;
    }

    @Override
    public List<?> findAll() {
        return null;
    }
}
