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
    RestaurantDao<EmployeePost> hibernateEmployeePostDao(SessionFactory localSessionFactoryBean) {

        HibernateEmployeePostDao hibernateEmployeePostDao = new HibernateEmployeePostDao();
        hibernateEmployeePostDao.setSessionFactory(localSessionFactoryBean);

        return hibernateEmployeePostDao;
    }

    @Bean
    RestaurantDao<Employee> hibernateEmployeeDao(SessionFactory localSessionFactoryBean) {

        HibernateEmployeeDao hibernateEmployeeDao = new HibernateEmployeeDao();
        hibernateEmployeeDao.setSessionFactory(localSessionFactoryBean);

        return hibernateEmployeeDao;
    }

    @Bean
    RestaurantDao<Ingredient> hibernateIngredientDao(SessionFactory localSessionFactoryBean) {

        HibernateIngredientDao ingredientDao = new HibernateIngredientDao();
        ingredientDao.setSessionFactory(localSessionFactoryBean);

        return ingredientDao;
    }

    @Bean
    RestaurantDao<Dish> hibernateDishDao(SessionFactory localSessionFactoryBean) {

        HibernateDishDao dishDao = new HibernateDishDao();
        dishDao.setSessionFactory(localSessionFactoryBean);

        return dishDao;
    }

    @Bean
    RestaurantDao<Menu> hibernateMenuDao(SessionFactory localSessionFactoryBean) {

        HibernateMenuDao menuDao = new HibernateMenuDao();
        menuDao.setSessionFactory(localSessionFactoryBean);

        return menuDao;
    }

    @Bean
    RestaurantDao<Order> hibernateOrderDao(SessionFactory localSessionFactoryBean) {
        HibernateOrderDao orderDao = new HibernateOrderDao();
        orderDao.setSessionFactory(localSessionFactoryBean);
        return orderDao;
    }

    @Bean
    RestaurantDao<KitchenProcess> hibernateKitchenDao(SessionFactory localSessionFactoryBean) {
        HibernateKitchenDao hibernateKitchenDao = new HibernateKitchenDao();
        hibernateKitchenDao.setSessionFactory(localSessionFactoryBean);
        return hibernateKitchenDao;
    }

    @Bean
    RestaurantDao<Unit> hibernateUnitDao(SessionFactory localSessionFactoryBean) {
        HibernateUnitsDao hibernateUnitsDao = new HibernateUnitsDao();
        hibernateUnitsDao.setSessionFactory(localSessionFactoryBean);
        return hibernateUnitsDao;
    }

    @Bean
    RestaurantDao<Contractor> hibernateSupplierDao(SessionFactory localSessionFactoryBean) {
        HibernateContractorsDao hibernateContractorsDao = new HibernateContractorsDao();
        hibernateContractorsDao.setSessionFactory(localSessionFactoryBean);
        return hibernateContractorsDao;
    }

    @Bean
    RestaurantDao<PurchaseInvoice> hibernatePurchaseInvoiceDao(SessionFactory localSessionFactoryBean) {
        HibernatePurchaseInvoiceDao hibernatePurchaseInvoiceDao = new HibernatePurchaseInvoiceDao();
        hibernatePurchaseInvoiceDao.setSessionFactory(localSessionFactoryBean);
        return hibernatePurchaseInvoiceDao;
    }

    @Bean
    RestaurantDao<SalesInvoice> hibernateSalesInvoiceDao(SessionFactory localSessionFactoryBean) {
        HibernateSalesInvoiceDao hibernateSalesInvoiceDao = new HibernateSalesInvoiceDao();
        hibernateSalesInvoiceDao.setSessionFactory(localSessionFactoryBean);
        return hibernateSalesInvoiceDao;
    }

    @Bean
    RestaurantDao<WarehousePosition> hibernateWarehouseDao(SessionFactory localSessionFactoryBean) {
        HibernateWarehouseDao hibernateWarehouseDao = new HibernateWarehouseDao();
        hibernateWarehouseDao.setSessionFactory(localSessionFactoryBean);
        return hibernateWarehouseDao;
    }

    @Bean
    HibernateTransactionManager hibernateTransactionManager(SessionFactory localSessionFactoryBean) {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(localSessionFactoryBean);
        return transactionManager;
    }

    @Bean
    CurrencyDao currencyDao(SessionFactory localSessionFactoryBean) {

        HibernateCurrenciesDao currenciesDao = new HibernateCurrenciesDao();
        currenciesDao.setSessionFactory(localSessionFactoryBean);

        return currenciesDao;
    }

    @Bean
    RestaurantDao<UserGroup> groupRestaurantDao(SessionFactory localSessionFactoryBean) {

        HibernateGroupsDao groupsDao = new HibernateGroupsDao();
        groupsDao.setSessionFactory(localSessionFactoryBean);

        return groupsDao;
    }

    @Bean
    RestaurantDao<UserImpl> userRestaurantDao(SessionFactory localSessionFactoryBean) {

        HibernateUsersDao userRestaurantDao = new HibernateUsersDao();
        userRestaurantDao.setSessionFactory(localSessionFactoryBean);

        return userRestaurantDao;
    }
}
