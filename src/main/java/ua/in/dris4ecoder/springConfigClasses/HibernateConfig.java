package ua.in.dris4ecoder.springConfigClasses;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import ua.in.dris4ecoder.model.businessObjects.Dish;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.model.businessObjects.Menu;
import ua.in.dris4ecoder.model.dao.RestaurantDao;
import ua.in.dris4ecoder.model.dao.hibernate.HibernateDishDao;
import ua.in.dris4ecoder.model.dao.hibernate.HibernateIngredientDao;
import ua.in.dris4ecoder.model.dao.hibernate.HibernateMenuDao;
import ua.in.dris4ecoder.model.dao.hibernate.HibernateOrderDao;

import java.util.Properties;

/**
 * Created by Alex Korneyko on 15.08.2016 11:18.
 */
@Configuration
public class HibernateConfig {

    @Bean
    LocalSessionFactoryBean localSessionFactoryBean(ComboPooledDataSource dataSource) {

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("ua.in.dris4ecoder.model");
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        sessionFactoryBean.setHibernateProperties(properties);

        return sessionFactoryBean;
    }

    @Bean
    RestaurantDao<Ingredient> hibernateIngredientDao(SessionFactory sessionFactory) {

        HibernateIngredientDao ingredientDao = new HibernateIngredientDao();
        ingredientDao.setSessionFactory(sessionFactory);

        return ingredientDao;
    }

    @Bean
    RestaurantDao<Dish> hibernateDishDao(SessionFactory sessionFactory) {

        HibernateDishDao dishDao = new HibernateDishDao();
        dishDao.setSessionFactory(sessionFactory);

        return dishDao;
    }

    @Bean
    RestaurantDao<Menu> hibernateMenuDao(SessionFactory sessionFactory) {

        HibernateMenuDao menuDao = new HibernateMenuDao();
        menuDao.setSessionFactory(sessionFactory);

        return menuDao;
    }

    @Bean
    RestaurantDao hibernateOrderDao(SessionFactory sessionFactory) {
        HibernateOrderDao orderDao = new HibernateOrderDao();
        orderDao.setSessionFactory(sessionFactory);
        return orderDao;
    }

    @Bean
    HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
