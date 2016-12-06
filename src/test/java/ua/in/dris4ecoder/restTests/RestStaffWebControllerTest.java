package ua.in.dris4ecoder.restTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.ResultActions;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import ua.in.dris4ecoder.BusinessObjectsFactory;
import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessServices.StaffService;
import ua.in.dris4ecoder.springTestConfigClasses.TestConfig;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;

/**
 * Created by admin on 06.12.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class RestStaffWebControllerTest {

    private static final String EMPLOYEE_POST = "CEO";
    private static final String ROLE = "ROLE_SUPERUSER";
    private static final String GROUP_NAME = "Employees";
    private static final String USER_NAME = "user";
    private static final String FIRST_NAME = "testFirstName";
    private static final String LAST_NAME = "testLastName";

    private MockMvc mockMvc;
    private Employee employee;

    @Autowired
    private BusinessObjectsFactory objectsFactory;

    @Autowired
    private StaffService staffService;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.annotationConfigSetup(TestConfig.class).build();

        objectsFactory.setEmployeeFirstName(FIRST_NAME);
        objectsFactory.setEmployeeLastName(LAST_NAME);
        objectsFactory.setEmployeePostName(EMPLOYEE_POST);
        objectsFactory.setUserGroupName(GROUP_NAME);
        objectsFactory.setUserName(USER_NAME);
        objectsFactory.setUserRoleName(ROLE);

        employee = objectsFactory.getEmployee();

        staffService.addEmployee(employee);
    }

    @After
    public void clearAll() {

        objectsFactory.clearAll();
    }

    @Test
    public void getEmployeeTest() throws Exception {

        ResultActions perform = mockMvc.perform(get("/employee/" + USER_NAME));
        String contentAsString = perform.andReturn().getResponse().getContentAsString();

        Employee employee = objectMapper.readValue(contentAsString, Employee.class);

        Employee employeeFromDB = staffService.findEmployeeByUserName(USER_NAME);

        Assert.assertEquals(employeeFromDB, employee);
    }
}
