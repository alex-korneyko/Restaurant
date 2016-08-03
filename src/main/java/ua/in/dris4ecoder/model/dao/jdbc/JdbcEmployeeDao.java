package ua.in.dris4ecoder.model.dao.jdbc;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.in.dris4ecoder.model.Employee;
import ua.in.dris4ecoder.model.KitchenProcess;
import ua.in.dris4ecoder.model.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
            statement.execute();
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

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE service.employees SET last_name = ?, first_name = ?, date_of_birth = ?, telephone = ?, post_id = ?, salary = ? WHERE id = ?")) {
            statement.setString(1, changedItem.getLastName());
            statement.setString(2, changedItem.getFirstName());
            statement.setDate(3, (java.sql.Date) changedItem.getDateOfBirth());
            statement.setString(4, changedItem.getTelephone());
            statement.setInt(5, changedItem.getPostId());
            statement.setDouble(6, changedItem.getSalary());
            statement.setInt(7, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee findItemById(int id) {

        ResultSet resultSet;
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM service.employees WHERE id = ?")){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            return createEmployee(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> findItem(String name) {

        List<Employee> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM service.employees WHERE employees.first_name LIKE ? OR employees.last_name LIKE ?")) {
            statement.setString(1, name);
            statement.setString(2, name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(createEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Employee> findItem(Employee employee) {
        throw new NotImplementedException();
    }

    @Override
    public KitchenProcess findItem(int orderId) {
        throw new NotImplementedException();
    }

    @Override
    public List<Employee> findItem(OrderDishStatus status) {
        throw new NotImplementedException();
    }

    @Override
    public List<Employee> findItem(LocalDate startPeriod, LocalDate endPeriod) {

        List<Employee> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM service.employees WHERE date_of_birth > ? AND date_of_birth < ?")) {
            statement.setDate(1, java.sql.Date.valueOf(startPeriod));
            statement.setDate(2, java.sql.Date.valueOf(endPeriod));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(createEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM service.employees");

            while (resultSet.next()) {
                employees.add(createEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        return new Employee(resultSet.getInt("id"), resultSet.getString("last_name"), resultSet.getString("first_name"),
                resultSet.getDate("date_of_birth"), resultSet.getString("telephone"), resultSet.getInt("post_id"), resultSet.getDouble("salary"));
    }
}
