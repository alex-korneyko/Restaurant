package ua.in.dris4ecoder.springConfigClasses;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcEmployeeDao;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcEmployeePostsDao;

/**
 * Created by Alex Korneyko on 15.08.2016 11:26.
 */
@Configuration
public class JdbcConfig {

    @Bean
    JdbcEmployeePostsDao jdbcEmployeePostsDao(ComboPooledDataSource comboPooledDataSourceForRestaurant) {
        final JdbcEmployeePostsDao jdbcEmployeePostsDao = new JdbcEmployeePostsDao();
        jdbcEmployeePostsDao.setDataSource(comboPooledDataSourceForRestaurant);
        return jdbcEmployeePostsDao;
    }

    @Bean
    JdbcEmployeeDao jdbcEmployeeDao(ComboPooledDataSource dataSource, JdbcEmployeePostsDao jdbcEmployeePostsDao) {
        final JdbcEmployeeDao jdbcEmployeeDao = new JdbcEmployeeDao();
        jdbcEmployeeDao.setDataSource(dataSource);
        jdbcEmployeeDao.setEmployeePostDao(jdbcEmployeePostsDao);
        return jdbcEmployeeDao;
    }
}
