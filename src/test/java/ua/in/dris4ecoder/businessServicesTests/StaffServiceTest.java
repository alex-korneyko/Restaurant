package ua.in.dris4ecoder.businessServicesTests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.in.dris4ecoder.BusinessObjectsFactory;
import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessServices.StaffService;
import ua.in.dris4ecoder.springTestConfigClasses.JpaTestConfig;
import ua.in.dris4ecoder.springTestConfigClasses.TestConfig;

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

            objectsFactory.setEmployeeFirstName(FIRST_NAME);
            objectsFactory.setEmployeeLastName(LAST_NAME);
            objectsFactory.setEmployeePostName(EMPLOYEE_POST);
            objectsFactory.setUserGroupName(GROUP_NAME);
            objectsFactory.setUserName(USER_NAME);
            objectsFactory.setUserRoleName(ROLE);

            employee = objectsFactory.getEmployee();
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


        int id = staffService.addEmployee(employee);

        Assert.assertNotEquals(0, id);
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
