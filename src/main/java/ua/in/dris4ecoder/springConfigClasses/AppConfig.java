package ua.in.dris4ecoder.springConfigClasses;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.controllers.ServiceController;
import ua.in.dris4ecoder.controllers.StaffController;
import ua.in.dris4ecoder.gui.windowsSet.MainWindow;
import ua.in.dris4ecoder.gui.windowsSet.SimpleMainWindow;
import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;
import ua.in.dris4ecoder.model.businessObjects.KitchenProcess;
import ua.in.dris4ecoder.model.businessObjects.Order;
import ua.in.dris4ecoder.model.dao.RestaurantDao;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcEmployeeDao;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcEmployeePostsDao;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcKitchenProcessDao;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcOrderDao;

import java.beans.PropertyVetoException;

/**
 * Created by Alex Korneyko on 28.07.2016 15:00.
 */
@Configuration
public class AppConfig {

    @Bean
    Main main(StaffController staffController, ServiceController serviceController, MainWindow mainWindow) {
        Main mainObject = new Main();

        mainObject.setStaffController(staffController);
        mainObject.setServiceController(serviceController);
        mainObject.setMainWindow(mainWindow);

        return mainObject;
    }

    @Bean
    ComboPooledDataSource comboPooledDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setDriverClass("org.postgresql.Driver");
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/restaurant");
        dataSource.setUser("postgres");
        dataSource.setPassword("8ergamot");
        dataSource.setMinPoolSize(1);
        dataSource.setMaxPoolSize(10);
        dataSource.setAcquireIncrement(1);

        return dataSource;
    }

    @Bean
    RestaurantDao jdbcRestaurantDao(ComboPooledDataSource dataSource) {
        JdbcEmployeePostsDao jdbcEmployeePostsDao = new JdbcEmployeePostsDao();
        jdbcEmployeePostsDao.setDataSource(dataSource);
        return jdbcEmployeePostsDao;
    }

    @Bean
    StaffController staffController(RestaurantDao<EmployeePost> jdbcEmployeePostsDao, RestaurantDao<Employee> jdbcEmployeeDao) {
        StaffController controller = new StaffController();
        controller.setEmployeePostsDao(jdbcEmployeePostsDao);
        controller.setEmployeeDao(jdbcEmployeeDao);
        return controller;
    }

    @Bean
    ServiceController serviceController(RestaurantDao<Order> jdbcOrderDao, RestaurantDao<KitchenProcess> jdbcKitchenProcessDao) {
        final ServiceController serviceController = new ServiceController();
        serviceController.setOrdersDao(jdbcOrderDao);
        serviceController.setKitchenProcessDao(jdbcKitchenProcessDao);
        return serviceController;
    }

    @Bean
    JdbcEmployeePostsDao jdbcEmployeePostsDao(ComboPooledDataSource comboPooledDataSource) {
        final JdbcEmployeePostsDao jdbcEmployeePostsDao = new JdbcEmployeePostsDao();
        jdbcEmployeePostsDao.setDataSource(comboPooledDataSource);
        return jdbcEmployeePostsDao;
    }

    @Bean
    JdbcEmployeeDao jdbcEmployeeDao(ComboPooledDataSource comboPooledDataSource) {
        final JdbcEmployeeDao jdbcEmployeeDao = new JdbcEmployeeDao();
        jdbcEmployeeDao.setDataSource(comboPooledDataSource);
        return jdbcEmployeeDao;
    }

    @Bean
    JdbcOrderDao jdbcOrderDao(ComboPooledDataSource comboPooledDataSource) {
        final JdbcOrderDao jdbcOrderDao = new JdbcOrderDao();
        jdbcOrderDao.setDataSource(comboPooledDataSource);
        return jdbcOrderDao;
    }

    @Bean
    JdbcKitchenProcessDao jdbcKitchenProcessDao(ComboPooledDataSource comboPooledDataSource) {
        final JdbcKitchenProcessDao jdbcKitchenProcessDao = new JdbcKitchenProcessDao();
        jdbcKitchenProcessDao.setDataSource(comboPooledDataSource);
        return jdbcKitchenProcessDao;
    }

    @Bean
    DataSourceTransactionManager transactionManager(ComboPooledDataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
