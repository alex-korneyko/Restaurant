package ua.in.dris4ecoder.springConfigClasses;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcEmployeeDao;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcEmployeePostsDao;

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
    JdbcEmployeeDao jdbcEmployeeDao(ComboPooledDataSource comboPooledDataSource, JdbcEmployeePostsDao jdbcEmployeePostsDao) {
        final JdbcEmployeeDao jdbcEmployeeDao = new JdbcEmployeeDao();
        jdbcEmployeeDao.setDataSource(comboPooledDataSource);
        jdbcEmployeeDao.setEmployeePostDao(jdbcEmployeePostsDao);
        return jdbcEmployeeDao;
    }

//    @Bean
//    DataSourceTransactionManager transactionManager(ComboPooledDataSource dataSource) {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(dataSource);
//        return dataSourceTransactionManager;
//    }
}
