package ua.in.dris4ecoder.model.dao.jdbc;

import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessObjects.KitchenProcess;
import ua.in.dris4ecoder.model.businessObjects.Order;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alex Korneyko on 04.08.2016 10:47.
 */
public class JdbcOrderDao implements RestaurantDao<Order> {

    private DataSource dataSource;

    @Override
    public void addItem(Order item) {

    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void editItem(int id, Order changedItem) {

    }

    @Override
    public Order findItemById(int id) {
        return null;
    }

    @Override
    public List<Order> findItem(String name) {
        return null;
    }

    @Override
    public List<Order> findItem(Order employee) {
        return null;
    }

    @Override
    public KitchenProcess findItem(int orderId) {
        return null;
    }

    @Override
    public List<Order> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<Order> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
