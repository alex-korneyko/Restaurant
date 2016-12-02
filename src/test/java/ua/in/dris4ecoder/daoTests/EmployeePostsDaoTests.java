package ua.in.dris4ecoder.daoTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;
import ua.in.dris4ecoder.model.dao.RestaurantDao;
import ua.in.dris4ecoder.springTestConfigClasses.JpaTestConfig;
import ua.in.dris4ecoder.springTestConfigClasses.TestConfig;

import static org.junit.Assert.*;

/**
 * Created by admin on 30.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, TestConfig.class})
@WebAppConfiguration
public class EmployeePostsDaoTests {

    private static final String CEO = "CEO" ;
    private static final EmployeePost EMPLOYEE_POST = new EmployeePost(CEO);
    private int id;

    @Autowired
    @Qualifier("employeePostRestaurantDao")
    private RestaurantDao<EmployeePost> employeePostDao;

    @Before
    @Test
    public void addEmployeePostTest() {

        id = employeePostDao.addItem(EMPLOYEE_POST);

        assertNotNull(id);
    }

    @Test
    public void getEmployeePostTest() {

        EmployeePost itemById = employeePostDao.findItemById(id);

        assertNotNull(itemById);
        assertEquals(id, itemById.getId());
    }

    @Test
    public void findEmployeePostByNameTest() {

        EmployeePost employeePost = employeePostDao.findItem(CEO);

        assertNotNull(employeePost);
        assertEquals(id, employeePost.getId());
    }

    @After
    @Test
    public void removeEmployeePostTest() {

        employeePostDao.removeItemById(id);

        EmployeePost itemById = employeePostDao.findItemById(id);

        assertNull(itemById);
    }
}
