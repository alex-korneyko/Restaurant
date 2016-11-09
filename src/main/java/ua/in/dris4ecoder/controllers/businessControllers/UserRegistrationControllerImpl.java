package ua.in.dris4ecoder.controllers.businessControllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.in.dris4ecoder.model.businessObjects.UserGroup;
import ua.in.dris4ecoder.model.businessObjects.UserImpl;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex Korneyko on 27.10.2016 19:01.
 */
public class UserRegistrationControllerImpl implements UserRegistrationController {

    private RestaurantDao<UserImpl> userRestaurantDao;
    private RestaurantDao<UserGroup> userGroupRestaurantDao;
    private BCryptPasswordEncoder passwordEncoder;

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
}
