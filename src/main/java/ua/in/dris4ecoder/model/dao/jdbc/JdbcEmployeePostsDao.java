package ua.in.dris4ecoder.model.dao.jdbc;

import ua.in.dris4ecoder.model.Employee;
import ua.in.dris4ecoder.model.EmployeePost;
import ua.in.dris4ecoder.model.KitchenProcess;
import ua.in.dris4ecoder.model.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex Korneyko on 28.07.2016 19:52.
 */
public class JdbcEmployeePostsDao implements RestaurantDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addItem(Object item) {
        if (!(item instanceof EmployeePost)) return;
        EmployeePost employeePost = ((EmployeePost) item);

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO service.employee_posts (post_name) VALUES ?")) {
            statement.setString(1, employeePost.getPostName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeItem(int id) {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM service.employee_posts WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editItem(int id, Object changedItem) {
        if (!(changedItem instanceof EmployeePost)) return;
        EmployeePost employeePost = ((EmployeePost) changedItem);

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE service.employee_posts SET post_name = ?")){
            statement.setString(1, employeePost.getPostName());
            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object findItem(String name) {

        EmployeePost employeePost;
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM service.employee_posts WHERE post_name LIKE ?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            employeePost = new EmployeePost(resultSet.getString("post_name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employeePost;
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

    public List<EmployeePost> findAll() {

        List<EmployeePost> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM service.employee_posts");

            while (resultSet.next()) {
                result.add(new EmployeePost(resultSet.getString("post_name")));
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
