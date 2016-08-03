package ua.in.dris4ecoder.model.dao.jdbc;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.in.dris4ecoder.model.Employee;
import ua.in.dris4ecoder.model.EmployeePost;
import ua.in.dris4ecoder.model.KitchenProcess;
import ua.in.dris4ecoder.model.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Korneyko on 28.07.2016 19:52.
 */
public class JdbcEmployeePostsDao implements RestaurantDao<EmployeePost> {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addItem(EmployeePost item) {

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO service.employee_posts (post_name) VALUES (?)")) {
            statement.setString(1, item.getPostName());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeItemById(int id) {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM service.employee_posts WHERE id = ?")) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeItemByName(String name) {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM service.employee_posts WHERE post_name = ?")) {
            statement.setString(1, name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editItem(int id, EmployeePost changedItem) {

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE service.employee_posts SET post_name = ? WHERE id = ?")){
            statement.setString(1, changedItem.getPostName());
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeePost findItemById(int id) {
        EmployeePost employeePost;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM service.employee_posts WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            employeePost = new EmployeePost(resultSet.getString("post_name"));
            employeePost.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employeePost;
    }

    @Override
    public List<EmployeePost> findItem(String name) {

        List<EmployeePost> employeePosts = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM service.employee_posts WHERE post_name LIKE ?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                employeePosts.add(new EmployeePost(resultSet.getInt("id") ,resultSet.getString("post_name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employeePosts;
    }

    @Override
    public List<EmployeePost> findItem(Employee employee) {
        throw new NotImplementedException();
    }

    @Override
    public KitchenProcess findItem(int orderId) {
        throw new NotImplementedException();
    }

    @Override
    public List<EmployeePost> findItem(OrderDishStatus status) {
        throw new NotImplementedException();
    }

    @Override
    public List<EmployeePost> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        throw new NotImplementedException();
    }

    @Override
    public List<EmployeePost> findAll() {

        List<EmployeePost> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM service.employee_posts");

            while (resultSet.next()) {
                EmployeePost post = new EmployeePost(resultSet.getString("post_name"));
                post.setId(resultSet.getInt("id"));
                result.add(post);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
