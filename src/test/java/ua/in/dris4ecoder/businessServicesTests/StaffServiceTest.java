package ua.in.dris4ecoder.businessServicesTests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessObjects.UserImpl;
import ua.in.dris4ecoder.model.businessServices.StaffService;
import ua.in.dris4ecoder.model.businessServices.UserRegistrationService;
import ua.in.dris4ecoder.springTestConfigClasses.JpaTestConfig;
import ua.in.dris4ecoder.springTestConfigClasses.TestConfig;

import java.util.Collections;

/**
 * Created by admin on 30.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = {JpaTestConfig.class, TestConfig.class})
@WebAppConfiguration
public class StaffServiceTest {

    private static final String EMPLOYEE_POST = "CEO";
    private static final String ROLE = "ROLE_SUPERUSER";
    private static final String GROUP_NAME = "Employees";

    @Autowired
    private StaffService staffService;

    @Autowired
    private UserRegistrationService userRegistrationService;

    private Employee employee;

    @Before
    public void setup() {

        if (staffService.findEmployeeByUserName("test") == null) {

            staffService.addEmployeePost(EMPLOYEE_POST);
            userRegistrationService.addUserRole(ROLE);
            userRegistrationService.addUserGroup(GROUP_NAME, Collections.singletonList(userRegistrationService.findUserRole(ROLE)));
        }

        UserImpl user = new UserImpl();
        user.setUserLogin("test");
        user.setUserPass("test");
        user.setUserGroups(Collections.singletonList(userRegistrationService.findUserGroup(GROUP_NAME)));

        employee = new Employee("TestLastName", "TestFirstName", staffService.findEmployeePost(EMPLOYEE_POST), user);
        employee.setUser(user);
    }

    @After
    public void clearAll() {

        if (staffService.findEmployeeByUserName("test") == null) {

            userRegistrationService.removeUserGroup(GROUP_NAME);
            userRegistrationService.removeUserRole(ROLE);
            staffService.removeEmployeePost(staffService.findEmployeePost(EMPLOYEE_POST).getId());
        }
    }

    @Test
    public void addEmployeeTest() {


        int id = staffService.addEmployee(employee);

        Assert.assertNotEquals(0, id);
    }

    @Test
    public void findEmployeeTest() {

        Employee employee = staffService.findEmployeeByUserName("test");

        Assert.assertNotNull(employee);
        Assert.assertEquals("test", employee.getUser().getUserLogin());
    }

    @Test
    public void removeEmployeeTest() {

        staffService.removeEmployee(staffService.findEmployeeByUserName("test").getId());

        Assert.assertNull(staffService.findEmployeeByUserName("test"));
    }



}
