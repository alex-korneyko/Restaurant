package ua.in.dris4ecoder.businessServicesTests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.in.dris4ecoder.BusinessObjectsFactory;
import ua.in.dris4ecoder.model.businessObjects.*;
import ua.in.dris4ecoder.model.businessServices.StaffService;
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
    private static final String USER_NAME = "user";
    private static final String FIRST_NAME = "testFirstName";
    private static final String LAST_NAME = "testLastName";

    @Autowired
    private StaffService staffService;

    @Autowired
    private BusinessObjectsFactory objectsFactory;

    private Employee employee;

    @Before
    public void setup() {

        if (staffService.findEmployeeByUserName(USER_NAME) == null) {

            EmployeePost employeePost = objectsFactory.createEmployeePost(EMPLOYEE_POST);
            UserRole userRole = objectsFactory.createUserRole(ROLE);
            UserGroup userGroup = objectsFactory.createUserGroup(GROUP_NAME, Collections.singletonList(userRole));
            UserImpl user = objectsFactory.createUser(USER_NAME, USER_NAME, userGroup);
            employee = objectsFactory.createEmployee(FIRST_NAME, LAST_NAME, employeePost, user, false);
        }
    }

    @After
    public void clearAll() {

        if (staffService.findEmployeeByUserName(USER_NAME) == null) {

            objectsFactory.clearAll();
        }
    }

    @Test
    public void addEmployeeTest() {


        Employee result = staffService.addEmployee(employee);

        Assert.assertNotEquals(0, result.getId());
    }

    @Test
    public void findEmployeeTest() {

        Employee employee = staffService.findEmployeeByUserName(USER_NAME);

        Assert.assertNotNull(employee);
        Assert.assertEquals(USER_NAME, employee.getUser().getUserLogin());
    }

    @Test
    public void removeEmployeeTest() {

        staffService.removeEmployee(staffService.findEmployeeByUserName(USER_NAME).getId());

        Assert.assertNull(staffService.findEmployeeByUserName(USER_NAME));
    }



}
