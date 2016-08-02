package ua.in.dris4ecoder.model.dao.jdbc;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.in.dris4ecoder.model.Employee;
import ua.in.dris4ecoder.model.KitchenProcess;
import ua.in.dris4ecoder.model.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                             "INSERT INTO service.employees (last_name, first_name, date_of_birth, telephone, post_id, salary) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, item.getLastName());
            statement.setString(2, item.getFirstName());
            statement.setDate(3, (java.sql.Date) item.getDateOfBirth());
            statement.setString(4, item.getTelephone());
            statement.setInt(5, item.getPostId());
            statement.setDouble(6, item.getSalary());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeItemById(int id) {

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM service.employees WHERE id = ?")) {
            statement.setInt(1, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeItemByName(String name) {
        throw new NotImplementedException();
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
