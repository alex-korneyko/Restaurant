package ua.in.dris4ecoder.controllers.businessControllers;


import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;
import ua.in.dris4ecoder.model.businessObjects.UserGroup;
import ua.in.dris4ecoder.model.businessObjects.UserImpl;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 28.07.2016 19:09.
 */
public class StaffController implements BusinessController {

    private RestaurantDao<EmployeePost> employeePostsDao;
    private RestaurantDao<Employee> employeeDao;
    private UserRegistrationController userRegistrationController;
    private GroupsRegistrationController groupsRegistrationController;

    public void addEmployeePost(String name) {
        employeePostsDao.addItem(new EmployeePost(name));
    }

    public void removeEmployeePost(int id) {
        employeePostsDao.removeItemById(id);
    }

    public void editEmployeePost(int id, String newEmployeePostName) {
        employeePostsDao.editItem(id, new EmployeePost(newEmployeePostName));
    }

    public EmployeePost findEmployeePost(String name) {
        return employeePostsDao.findItem(name);
    }

    public EmployeePost findEmployeePostById(int id) {
        return employeePostsDao.findItemById(id);
    }

    public List<EmployeePost> getAllEmployeePosts() {
        return employeePostsDao.findAll();
    }


    public void addEmployee(String lastName, String firstName, int postId) {

        employeeDao.addItem(new Employee(lastName, firstName, Main.getStaffController().findEmployeePostById(postId)));
    }

    public void addEmployee(Employee employee) {

        userRegistrationController.addUser(employee.getUser());
        employeeDao.addItem(employee);
    }

    public void addEmployee(Map<String, String> params) {

        Employee employee = createEditEmployeeFromWebForm(params);

        addEmployee(employee);
    }

    private Employee createEditEmployeeFromWebForm(Map<String, String> params) {

        Employee employee;

        //if in 'params' object 'employeeId' is empty, create new Employee, if not, then get Employee from DB
        if (params.get("employeeId").equals("")) {
            employee = new Employee();
            employee.setUser(new UserImpl());
        } else {
            employee = findEmployeeById(Integer.parseInt(params.get("employeeId")));
        }

        employee.setFirstName(params.get("userName"));
        employee.setLastName(params.get("userSurName"));
        employee.setPhoneNumber(params.get("newEmployeePhone"));
        employee.setDateOfBirth(LocalDate.parse(params.get("newEmployeeDateOfBirth")));
        employee.setSalary(Double.parseDouble(params.get("newEmployeeSalary")));

        fillUserData(params, employee);

        return employee;
    }

    private void fillUserData(Map<String, String> params, Employee employee) {
        EmployeePost employeePost = employeePostsDao.findItem(params.get("employeePost"));
        employee.setEmployeePost(employeePost);

        List<String> selectedGroups = params.keySet().stream()
                .filter(s -> s.length() > 8 && s.substring(0, 8).equals("selected")).collect(Collectors.toList());

        List<UserGroup> userGroups = new ArrayList<>();

        selectedGroups.forEach(s -> userGroups.add(groupsRegistrationController.findUserGroup(Integer.parseInt(params.get(s)))));

        employee.getUser().setUserName(params.get("userName"));
        employee.getUser().setUserSurName(params.get("userSurName"));
        employee.getUser().setUserLogin(params.get("userLogin"));
        employee.getUser().setEnabled(true);
        employee.getUser().setUserGroups(userGroups);

        if (!params.get("userPass1").equals("")) {
            employee.getUser().setUserPass(params.get("userPass1"));
        }
    }

    public void removeEmployee(int id) {
        Employee employee = employeeDao.findItemById(id);
        employeeDao.removeItemById(id);
        userRegistrationController.removeUser(employee.getUser().getUserLogin());
    }

    public void editEmployee(int id, Employee changedEmployee) {
        userRegistrationController.editUser(changedEmployee.getUser());
        employeeDao.editItem(id, changedEmployee);
    }

    public void editEmployee(Map<String, String> params) {

        editEmployee(Integer.parseInt(params.get("employeeId")), createEditEmployeeFromWebForm(params));
    }

    public Employee findEmployeeByName(String name) {
        return employeeDao.findItem(name);
    }

    public Employee findEmployeeById(int id) {
        return employeeDao.findItemById(id);
    }

    public List<Employee> findEmployeeByDateRange(LocalDateTime start, LocalDateTime end) {
        return employeeDao.findItem(start, end);
    }


    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    public void setEmployeeDao(RestaurantDao<Employee> employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setEmployeePostsDao(RestaurantDao<EmployeePost> employeePostsDao) {
        this.employeePostsDao = employeePostsDao;
    }

    public void setUserRegistrationController(UserRegistrationController userRegistrationController) {
        this.userRegistrationController = userRegistrationController;
    }

    public void setGroupsRegistrationController(GroupsRegistrationController groupsRegistrationController) {
        this.groupsRegistrationController = groupsRegistrationController;
    }

    public Employee findEmployeeByUserName(String name) {

        return employeeDao.findItem(name);
    }

    public String validateEmployeeFormData(Map<String, String> params) {

        if (params.get("employeeId").equals("") && (params.get("userPass1").length() == 0 || params.get("userPass2").length() == 0)) {
            return "Пароль слишком короткий. Минимум 4 символа";
        }
        if (params.get("userPass1").length() > 20 || params.get("userPass2").length() > 20) {
            return "Пароль слишком длинный. Максимум 20 символов";
        }
        if (!params.get("userPass1").equals(params.get("userPass2"))) {
            return "Пароли не совпадают";
        }

        for (String s : params.keySet()) {
            if (params.get(s).length() == 0 &&
                    (!s.equals("userPass1") && !s.equals("userPass2") && !s.equals("employeeId") && !s.equals("saveEmployeeForm")))
                return "Некоторые поля не заполнены (" + s + ")";
        }

        if (params.get("employeeId").isEmpty() && userRegistrationController.findUser(params.get("userLogin")) != null) {
            return "Пользователь с логином '" + params.get("userLogin") + "' уже существует";
        }

        return "";
    }
}
