package ua.in.dris4ecoder;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import ua.in.dris4ecoder.controllers.StaffController;
import ua.in.dris4ecoder.model.dao.RestaurantDao;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcEmployeeDao;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcEmployeePostsDao;

import java.beans.PropertyVetoException;

/**
 * Created by Alex Korneyko on 28.07.2016 15:00.
 */
@Configuration
public class AppConfig {

    @Bean
    Main main(StaffController controller, RestaurantDao jdbcEmployeePostsDao) {
        Main mainObject = new Main();

        mainObject.setStaffController(controller);
//        mainObject.setRestaurantDao(jdbcEmployeePostsDao);

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
    StaffController staffController(RestaurantDao jdbcEmployeePostsDao, RestaurantDao jdbcEmployeeDao) {
        StaffController controller = new StaffController();
        controller.setEmployeePostsDao(jdbcEmployeePostsDao);
        controller.setEmployeeDao(jdbcEmployeeDao);
        return controller;
    }

    @Bean
    JdbcEmployeePostsDao jdbcEmployeePostsDao() {
        return new JdbcEmployeePostsDao();
    }

    @Bean
    JdbcEmployeeDao jdbcEmployeeDao() {
        return new JdbcEmployeeDao();
    }

    @Bean
    DataSourceTransactionManager transactionManager(ComboPooledDataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
