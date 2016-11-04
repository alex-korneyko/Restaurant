package ua.in.dris4ecoder.springConfigClasses;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.in.dris4ecoder.model.businessObjects.*;
import ua.in.dris4ecoder.model.dao.CurrencyDao;
import ua.in.dris4ecoder.model.dao.RestaurantDao;
import ua.in.dris4ecoder.model.dao.hibernate.*;

import java.util.Properties;

/**
 * Created by Alex Korneyko on 15.08.2016 11:18.
 */
@Configuration
@EnableTransactionManagement
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
    RestaurantDao<EmployeePost> hibernateEmployeePostDao(SessionFactory sessionFactory) {

        HibernateEmployeePostDao hibernateEmployeePostDao = new HibernateEmployeePostDao();
        hibernateEmployeePostDao.setSessionFactory(sessionFactory);

        return hibernateEmployeePostDao;
    }

    @Bean
    RestaurantDao<Employee> hibernateEmployeeDao(SessionFactory sessionFactory) {

        HibernateEmployeeDao hibernateEmployeeDao = new HibernateEmployeeDao();
        hibernateEmployeeDao.setSessionFactory(sessionFactory);

        return hibernateEmployeeDao;
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
    RestaurantDao<Order> hibernateOrderDao(SessionFactory sessionFactory) {
        HibernateOrderDao orderDao = new HibernateOrderDao();
        orderDao.setSessionFactory(sessionFactory);
        return orderDao;
    }

    @Bean
    RestaurantDao<KitchenProcess> hibernateKitchenDao(SessionFactory sessionFactory) {
        HibernateKitchenDao hibernateKitchenDao = new HibernateKitchenDao();
        hibernateKitchenDao.setSessionFactory(sessionFactory);
        return hibernateKitchenDao;
    }

    @Bean
    RestaurantDao<Unit> hibernateUnitDao(SessionFactory sessionFactory) {
        HibernateUnitsDao hibernateUnitsDao = new HibernateUnitsDao();
        hibernateUnitsDao.setSessionFactory(sessionFactory);
        return hibernateUnitsDao;
    }

    @Bean
    RestaurantDao<Contractor> hibernateSupplierDao(SessionFactory sessionFactory) {
        HibernateContractorsDao hibernateContractorsDao = new HibernateContractorsDao();
        hibernateContractorsDao.setSessionFactory(sessionFactory);
        return hibernateContractorsDao;
    }

    @Bean
    RestaurantDao<PurchaseInvoice> hibernatePurchaseInvoiceDao(SessionFactory sessionFactory) {
        HibernatePurchaseInvoiceDao hibernatePurchaseInvoiceDao = new HibernatePurchaseInvoiceDao();
        hibernatePurchaseInvoiceDao.setSessionFactory(sessionFactory);
        return hibernatePurchaseInvoiceDao;
    }

    @Bean
    RestaurantDao<SalesInvoice> hibernateSalesInvoiceDao(SessionFactory sessionFactory) {
        HibernateSalesInvoiceDao hibernateSalesInvoiceDao = new HibernateSalesInvoiceDao();
        hibernateSalesInvoiceDao.setSessionFactory(sessionFactory);
        return hibernateSalesInvoiceDao;
    }

    @Bean
    RestaurantDao<WarehousePosition> hibernateWarehouseDao(SessionFactory sessionFactory) {
        HibernateWarehouseDao hibernateWarehouseDao = new HibernateWarehouseDao();
        hibernateWarehouseDao.setSessionFactory(sessionFactory);
        return hibernateWarehouseDao;
    }

    @Bean
    HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    CurrencyDao currencyDao(SessionFactory sessionFactory) {

        HibernateCurrenciesDao currenciesDao = new HibernateCurrenciesDao();
        currenciesDao.setSessionFactory(sessionFactory);

        return currenciesDao;
    }

    @Bean
    RestaurantDao<UserGroup> groupRestaurantDao(SessionFactory sessionFactory) {

        HibernateGroupsDao groupsDao = new HibernateGroupsDao();
        groupsDao.setSessionFactory(sessionFactory);

        return groupsDao;
    }

    @Bean
    RestaurantDao<User> userRestaurantDao(SessionFactory localSessionFactoryBean) {

        HibernateUsersDao userRestaurantDao = new HibernateUsersDao();
        userRestaurantDao.setSessionFactory(localSessionFactoryBean);

        return userRestaurantDao;
    }
}
