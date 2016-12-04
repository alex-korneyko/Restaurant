package ua.in.dris4ecoder.model.businessServices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.in.dris4ecoder.model.businessObjects.UserGroup;
import ua.in.dris4ecoder.model.businessObjects.UserImpl;
import ua.in.dris4ecoder.model.businessObjects.UserRole;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex Korneyko on 27.10.2016 19:01.
 */
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private RestaurantDao<UserImpl> userRestaurantDao;
    private RestaurantDao<UserGroup> userGroupRestaurantDao;
    private RestaurantDao<UserRole> userRoleRestaurantDao;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void addUserRole(String userRole) {

       addUserRole(new UserRole(userRole));
    }

    @Override
    public void addUserRole(UserRole userRole) {

        userRoleRestaurantDao.addItem(userRole);
    }

    @Override
    public UserRole findUserRole(String roleName) {

        return userRoleRestaurantDao.findItem(roleName);
    }

    @Override
    public void editUserRole(UserRole changedUserRole) {

        userRoleRestaurantDao.editItem(0, changedUserRole);
    }

    @Override
    public void removeUserRole(UserRole userRole) {

        userRoleRestaurantDao.removeItem(userRole);
    }

    @Override
    public void removeUserRole(String userRoleName) {

    }

    @Override
    public void validateUserGroup(UserGroup userGroup) {

    }

    @Override
    public void addUserGroup(UserGroup userGroup) {

        userGroupRestaurantDao.addItem(userGroup);
    }

    @Override
    public void addUserGroup(String groupName, List<UserRole> groupAuthorities) {

        userGroupRestaurantDao.addItem(new UserGroup(groupName, groupAuthorities));
    }

    @Override
    public UserGroup findUserGroup(int userGroupId) {

        return userGroupRestaurantDao.findItemById(userGroupId);
    }

    @Override
    public UserGroup findUserGroup(UserGroup userGroup) {
        return null;
    }

    @Override
    public UserGroup findUserGroup(String userGroupName) {
        return null;
    }

    @Override
    public List<UserGroup> getAllGroups() {

        return userGroupRestaurantDao.findAll();
    }

    @Override
    public void editUserGroup(UserGroup userGroup) {

    }

    @Override
    public void removeUserGroup(int userGroupId) {

    }

    @Override
    public void removeUserGroup(UserGroup userGroup) {

    }

    @Override
    public void removeUserGroup(String userGroupName) {

        userGroupRestaurantDao.removeItemByName(userGroupName);
    }

    @Override
    public void validateUser(UserImpl user) {

    }

    @Override
    public void addUser(UserImpl user) {

        user.setUserPass(passwordEncoder.encode(user.getUserPass()));
        userRestaurantDao.addItem(user);
    }

    @Override
    public void addUser(Map<String, String> userData) {

        UserImpl user = new UserImpl();
        user.setUserName(userData.get("userName"));
        user.setUserSurName(userData.get("userSurName"));
        user.setUserLogin(userData.get("userLogin"));
        user.setUserPass(userData.get("userPass1"));
        user.setUserGroups(Collections.singletonList(userGroupRestaurantDao.findItem("Users")));
        user.setEnabled(true);

        addUser(user);
    }

    @Override
    public void editUser(UserImpl user) {

        if (user.getUserPass().length() < 50) {
            user.setUserPass(passwordEncoder.encode(user.getUserPass()));
        }

        userRestaurantDao.editItem(0, user);
    }

    @Override
    public UserImpl findUser(UserImpl user) {

        return user;
    }

    @Override
    public UserImpl findUser(String userLogin) {

        return userRestaurantDao.findItem(userLogin);
    }

    @Override
    public void removeUser(UserImpl user) {

    }

    @Override
    public void removeUser(String userLogin) {

        userRestaurantDao.removeItemByName(userLogin);
    }

    @Override
    public List<UserImpl> getAllUsers() {

        return userRestaurantDao.findAll();
    }

    public void setUserRestaurantDao(RestaurantDao<UserImpl> userRestaurantDao) {
        this.userRestaurantDao = userRestaurantDao;
    }

    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void setUserGroupRestaurantDao(RestaurantDao<UserGroup> userGroupRestaurantDao) {
        this.userGroupRestaurantDao = userGroupRestaurantDao;
    }

    public void setUserRoleRestaurantDao(RestaurantDao<UserRole> userRoleRestaurantDao) {
        this.userRoleRestaurantDao = userRoleRestaurantDao;
    }
}
