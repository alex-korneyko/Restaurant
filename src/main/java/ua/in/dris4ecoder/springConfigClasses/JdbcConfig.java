package ua.in.dris4ecoder.springConfigClasses;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcEmployeeDao;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcEmployeePostsDao;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcKitchenProcessDao;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcOrderDao;

/**
 * Created by Alex Korneyko on 15.08.2016 11:26.
 */
@Configuration
public class JdbcConfig {

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
