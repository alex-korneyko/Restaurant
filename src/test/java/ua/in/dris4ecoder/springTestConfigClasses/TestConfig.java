package ua.in.dris4ecoder.springTestConfigClasses;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;
import ua.in.dris4ecoder.model.businessServices.BusinessService;
import ua.in.dris4ecoder.model.businessServices.StaffService;
import ua.in.dris4ecoder.model.dao.RestaurantDao;
import ua.in.dris4ecoder.model.dao.hibernate.HibernateEmployeePostDao;

/**
 * Created by admin on 30.11.2016.
 */
@Configuration
@ComponentScan(basePackages = {
        "ua.in.dris4ecoder.controllers",
        "ua.in.dris4ecoder.model",
        "ua.in.dris4ecoder.view"
})
public class TestConfig {

//    @Bean
//    public BusinessService staffService() {
//
//        return new StaffService();
//    }
//
//    @Bean
//    public RestaurantDao<EmployeePost> employeePostRestaurantDao(SessionFactory testSessionFactoryBean) {
//
//        HibernateEmployeePostDao employeePostDao = new HibernateEmployeePostDao();
//        employeePostDao.setSessionFactory(testSessionFactoryBean);
//
//        return employeePostDao;
//    }


}
