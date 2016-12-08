package ua.in.dris4ecoder.restTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.ResultActions;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import ua.in.dris4ecoder.BusinessObjectsFactory;
import ua.in.dris4ecoder.model.businessObjects.*;
import ua.in.dris4ecoder.model.businessServices.StaffService;
import ua.in.dris4ecoder.springTestConfigClasses.TestConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Autowired
    private BusinessObjectsFactory objectsFactory;

    @Autowired
    private StaffService staffService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeClass
    public static void init() {

    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.annotationConfigSetup(TestConfig.class).build();

        EmployeePost employeePost = objectsFactory.createEmployeePost(EMPLOYEE_POST);
        UserRole userRole = objectsFactory.createUserRole(ROLE);
        UserGroup userGroup = objectsFactory.createUserGroup(GROUP_NAME, Collections.singletonList(userRole));
        UserImpl user = objectsFactory.createUser(USER_NAME, USER_NAME, userGroup);
        objectsFactory.createEmployee(FIRST_NAME, LAST_NAME, employeePost, user, true);

        user = objectsFactory.createUser(USER_NAME + "2", USER_NAME, userGroup);
        objectsFactory.createEmployee(FIRST_NAME + "2", LAST_NAME + "2", employeePost, user, true);
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

    @Test
    public void getEmployeesTest() throws Exception {

        List<Employee> restEmployees = new ArrayList<>();

        ResultActions perform = mockMvc.perform(get("/employee"));
        String contentAsString = perform.andReturn().getResponse().getContentAsString();

        JSONArray jsonArray = new JSONArray(contentAsString);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Employee employee = objectMapper.readValue(jsonObject.toString(), Employee.class);
            restEmployees.add(employee);
        }

        List<Employee> allEmployeesFromDB = staffService.getAllEmployees();

        Assert.assertEquals(allEmployeesFromDB, restEmployees);
    }
}
