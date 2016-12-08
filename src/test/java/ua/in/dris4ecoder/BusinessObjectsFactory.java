package ua.in.dris4ecoder;

import ua.in.dris4ecoder.model.businessObjects.*;
import ua.in.dris4ecoder.model.businessServices.StaffService;
import ua.in.dris4ecoder.model.businessServices.UserRegistrationService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 06.12.2016.
 */
public class BusinessObjectsFactory {

    private UserRegistrationService userRegistrationService;
    private StaffService staffService;

    private List<Employee> employees = new ArrayList<>();
    private List<EmployeePost> posts = new ArrayList<>();
    private List<UserImpl> users = new ArrayList<>();
    private List<UserGroup> userGroups = new ArrayList<>();
    private List<UserRole> userRoles = new ArrayList<>();

    public Employee createEmployee(String employeeFirstName, String employeeLastName, EmployeePost employeePost,
                                   UserImpl user, boolean saveToDataBase) {

        user = userRegistrationService.findUser(user.getUserLogin());
        if (user == null) user = createUser(user.getUserName(), user.getUserPass(), user.getUserGroups().get(0));

        employeePost = staffService.findEmployeePost(employeePost.getPostName());
        if (employeePost == null) employeePost = createEmployeePost(employeePost.getPostName());

        Employee employee = new Employee(employeeLastName, employeeFirstName, employeePost, user);

        if (saveToDataBase) {
            employee = staffService.addEmployee(employee);
            employees.add(employee);
        }

        return employee;
    }

    public EmployeePost createEmployeePost(String employeePostName) {

        EmployeePost employeePost = staffService.addEmployeePost(employeePostName);
        posts.add(employeePost);

        return employeePost;
    }

    public UserImpl createUser(String userName, String userPass, UserGroup userGroup) {

        UserImpl user = new UserImpl();

        user.setUserLogin(userName);
        user.setUserPass(userPass);
        userGroup = userRegistrationService.findUserGroup(userGroup.getGroupName());
        if (userGroup == null) userGroup = createUserGroup(userGroup.getGroupName(), userGroup.getRoles());

        user.setUserGroups(Collections.singletonList(userGroup));

        UserImpl createdUser = userRegistrationService.addUser(user);

        users.add(createdUser);

        return createdUser;
    }

    public UserGroup createUserGroup(String userGroupName, List<UserRole> roles) {

        List<UserRole> userRoles = new ArrayList<>();

        for (UserRole role : roles) {
            UserRole userRole = userRegistrationService.findUserRole(role.getRoleName());
            if (userRole == null) {
                userRoles.add(createUserRole(role.getRoleName()));
            } else {
                userRoles.add(userRole);
            }
        }

        UserGroup userGroup = userRegistrationService.addUserGroup(userGroupName, userRoles);
        userGroups.add(userGroup);

        return userGroup;
    }

    public UserRole createUserRole(String userRoleName) {

        UserRole userRole = userRegistrationService.addUserRole(userRoleName);
        userRoles.add(userRole);

        return userRole;
    }

    public void clearAll() {

        employees.forEach(employee -> staffService.removeEmployee(employee.getId()));
        posts.forEach(post -> staffService.removeEmployeePost(post.getId()));
        userGroups.forEach(userGroup -> userRegistrationService.removeUserGroup(userGroup));
        userRoles.forEach(userRole -> userRegistrationService.removeUserRole(userRole));
    }

    public void setUserRegistrationService(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }
}
