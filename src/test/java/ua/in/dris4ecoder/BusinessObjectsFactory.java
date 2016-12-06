package ua.in.dris4ecoder;

import ua.in.dris4ecoder.model.businessObjects.*;
import ua.in.dris4ecoder.model.businessServices.StaffService;
import ua.in.dris4ecoder.model.businessServices.UserRegistrationService;

import java.util.Collections;

/**
 * Created by admin on 06.12.2016.
 */
public class BusinessObjectsFactory {

    private String userRoleName;
    private String userGroupName;
    private String userName;
    private String employeePostName;
    private String employeeFirstName;
    private String employeeLastName;

    private UserRegistrationService userRegistrationService;
    private StaffService staffService;

    public Employee getEmployee() {

        UserImpl user = userRegistrationService.findUser(userName);
        if (user == null) user = getUser();

        EmployeePost employeePost = staffService.findEmployeePost(employeePostName);
        if (employeePost == null) employeePost = getEmployeePost();

        return new Employee(employeeLastName, employeeFirstName, employeePost, user);
    }

    private EmployeePost getEmployeePost() {

        staffService.addEmployeePost(employeePostName);

        return staffService.findEmployeePost(employeePostName);
    }

    public UserImpl getUser() {

        UserImpl user = new UserImpl();

        user.setUserLogin(userName);
        user.setUserPass(userName);
        UserGroup userGroup = userRegistrationService.findUserGroup(userGroupName);
        if (userGroup == null) userGroup = getUserGroup();

        user.setUserGroups(Collections.singletonList(userGroup));

        return user;
    }

    public UserGroup getUserGroup() {

        UserRole userRole = userRegistrationService.findUserRole(userRoleName);
        if (userRole == null) userRole = getUserRole();

        userRegistrationService.addUserGroup(userGroupName, Collections.singletonList(userRole));

        return userRegistrationService.findUserGroup(userGroupName);
    }

    public UserRole getUserRole() {

        userRegistrationService.addUserRole(userRoleName);

        return userRegistrationService.findUserRole(userRoleName);
    }

    public void clearAll() {

        Employee employeeByUserName = staffService.findEmployeeByUserName(userName);
        if (employeeByUserName != null) {
            staffService.removeEmployee(employeeByUserName.getId());
        }

        userRegistrationService.removeUserGroup(userGroupName);
        userRegistrationService.removeUserRole(userRoleName);
        staffService.removeEmployeePost(staffService.findEmployeePost(employeePostName).getId());
    }

    public void setUserRegistrationService(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmployeePostName(String employeePostName) {
        this.employeePostName = employeePostName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }
}
