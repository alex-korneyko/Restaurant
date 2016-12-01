package ua.in.dris4ecoder.daoTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;
import ua.in.dris4ecoder.model.dao.RestaurantDao;
import ua.in.dris4ecoder.springTestConfigClasses.JpaTestConfig;
import ua.in.dris4ecoder.springTestConfigClasses.TestConfig;

/**
 * Created by admin on 30.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, TestConfig.class})
public class EmployeePostsDaoTests {

//    @Qualifier("employeePostRestaurantDao")
//    @Autowired
//    private RestaurantDao<EmployeePost> employeePostDao;

    @Test
    public void addEmployeePostTest() {

//        int id = employeePostDao.addItem(new EmployeePost("CEO"));
//        System.out.println(id);
    }
}
