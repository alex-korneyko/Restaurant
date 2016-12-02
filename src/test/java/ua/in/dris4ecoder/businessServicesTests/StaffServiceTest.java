package ua.in.dris4ecoder.businessServicesTests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;
import ua.in.dris4ecoder.model.businessObjects.UserImpl;
import ua.in.dris4ecoder.model.businessServices.StaffService;
import ua.in.dris4ecoder.springTestConfigClasses.JpaTestConfig;
import ua.in.dris4ecoder.springTestConfigClasses.TestConfig;

import java.util.Arrays;

/**
 * Created by admin on 30.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = {JpaTestConfig.class, TestConfig.class})
@WebAppConfiguration
public class StaffServiceTest {

    @Autowired
    private StaffService staffService;

    @Test
    public void addItemTest() {

        Employee employee = new Employee("Armstrong", "Neil", new EmployeePost("CEO"));
        UserImpl user = new UserImpl();
        user.setUserGroups(Arrays.asList());
        employee.setUser(user);

        int id = staffService.addEmployee(employee);

        Assert.assertNotEquals(0, id);
    }



}
