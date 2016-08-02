package ua.in.dris4ecoder.model.dao.jdbc;

import ua.in.dris4ecoder.model.Employee;
import ua.in.dris4ecoder.model.KitchenProcess;
import ua.in.dris4ecoder.model.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex Korneyko on 02.08.2016 12:28.
 */
public class JdbcEmployeeDao implements RestaurantDao<Employee> {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addItem(Employee item) {

    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void editItem(int id, Employee changedItem) {

    }

    @Override
    public Employee findItemById(int id) {
        return null;
    }

    @Override
    public Employee findItem(String name) {
        return null;
    }

    @Override
    public List<Employee> findItem(Employee employee) {
        return null;
    }

    @Override
    public KitchenProcess findItem(int orderId) {
        return null;
    }

    @Override
    public List<Employee> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<Employee> findItem(Date startPeriod, Date endPeriod) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }
}
