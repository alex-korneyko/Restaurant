package ua.in.dris4ecoder.springTestConfigClasses;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ua.in.dris4ecoder.model.businessObjects.*;
import ua.in.dris4ecoder.model.businessServices.*;
import ua.in.dris4ecoder.model.dao.RestaurantDao;
import ua.in.dris4ecoder.model.dao.hibernate.*;

/**
 * Created by admin on 30.11.2016.
 */
@Configuration
@ComponentScan(basePackages = {
        "ua.in.dris4ecoder.controllers",
        "ua.in.dris4ecoder.model",
        "ua.in.dris4ecoder.view",
        "ua.in.dris4ecoder.springTestConfigClasses"
})
public class TestConfig {

    @Bean
    public RestaurantDao<EmployeePost> employeePostRestaurantDao(
            @Qualifier("localSessionFactoryBean") SessionFactory testSessionFactoryBean) {

        HibernateEmployeePostDao employeePostDao = new HibernateEmployeePostDao();
        employeePostDao.setSessionFactory(testSessionFactoryBean);

        return employeePostDao;
    }

    @Bean
    public RestaurantDao<Employee> employeeRestaurantDao (SessionFactory sessionFactory) {

        HibernateEmployeeDao hibernateEmployeeDao = new HibernateEmployeeDao();
        hibernateEmployeeDao.setSessionFactory(sessionFactory);

        return hibernateEmployeeDao;
    }

    @Bean
    public RestaurantDao<UserRole> userRoleRestaurantDao(SessionFactory sessionFactory) {

        HibernateUserRolesDao userRolesDao = new HibernateUserRolesDao();
        userRolesDao.setSessionFactory(sessionFactory);

        return userRolesDao;
    }

    @Bean
    public RestaurantDao<UserGroup> userGroupRestaurantDao(SessionFactory sessionFactory) {

        HibernateGroupsDao groupsDao = new HibernateGroupsDao();
        groupsDao.setSessionFactory(sessionFactory);

        return groupsDao;
    }

    @Bean RestaurantDao<UserImpl> userRestaurantDaoTest(SessionFactory sessionFactory) {

        HibernateUsersDao hibernateUsersDao = new HibernateUsersDao();
        hibernateUsersDao.setSessionFactory(sessionFactory);

        return hibernateUsersDao;
    }

    @Bean
    public UserRegistrationServiceImpl userRegistrationService(RestaurantDao<UserRole> userRoleRestaurantDao,
                                                               RestaurantDao<UserGroup> userGroupRestaurantDao,
                                                               RestaurantDao<UserImpl> userRestaurantDaoTest,
                                                               BCryptPasswordEncoder bCryptPasswordEncoder) {

        UserRegistrationServiceImpl userRegistrationService = new UserRegistrationServiceImpl();
        userRegistrationService.setUserRoleRestaurantDao(userRoleRestaurantDao);
        userRegistrationService.setPasswordEncoder(bCryptPasswordEncoder);
        userRegistrationService.setUserGroupRestaurantDao(userGroupRestaurantDao);
        userRegistrationService.setUserRestaurantDao(userRestaurantDaoTest);

        return userRegistrationService;
    }

    @Bean
    public StaffService staffService(RestaurantDao<Employee> employeeRestaurantDao,
                                     RestaurantDao<EmployeePost> employeePostRestaurantDao,
                                     UserRegistrationServiceImpl userRegistrationService) {

        StaffService staffService = new StaffService();
        staffService.setEmployeeDao(employeeRestaurantDao);
        staffService.setEmployeePostsDao(employeePostRestaurantDao);
        staffService.setUserRegistrationService(userRegistrationService);

        return staffService;
    }
}
