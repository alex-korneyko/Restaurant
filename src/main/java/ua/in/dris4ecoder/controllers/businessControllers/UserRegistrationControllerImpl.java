package ua.in.dris4ecoder.controllers.businessControllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.in.dris4ecoder.model.businessObjects.User;
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

    private RestaurantDao<User> userRestaurantDao;
    private RestaurantDao<UserGroup> userGroupRestaurantDao;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void validateUser(User user) {

    }

    @Override
    public void addUser(User user) {

        user.setUserPass(passwordEncoder.encode(user.getUserPass()));
        userRestaurantDao.addItem(user);
    }

    @Override
    public void addUser(Map<String, String> userData) {

        User user = new UserImpl();
        user.setUserName(userData.get("userName"));
        user.setUserSurName(userData.get("userSurName"));
        user.setUserLogin(userData.get("userLogin"));
        user.setUserPass(userData.get("userPass1"));
        user.setUserGroups(Collections.singletonList(userGroupRestaurantDao.findItem("Users")));
        user.setEnabled(true);

        addUser(user);
    }

    @Override
    public void editUser(User user) {

        if (user.getUserPass().length() < 50) {
            user.setUserPass(passwordEncoder.encode(user.getUserPass()));
        }

        userRestaurantDao.editItem(0, user);
    }

    @Override
    public User findUser(User user) {

        return user;
    }

    @Override
    public User findUser(String userLogin) {

        return userRestaurantDao.findItem(userLogin);
    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public void removeUser(String userLogin) {

        userRestaurantDao.removeItemByName(userLogin);
    }

    @Override
    public List<User> getAllUsers() {

        return userRestaurantDao.findAll();
    }

    public void setUserRestaurantDao(RestaurantDao<User> userRestaurantDao) {
        this.userRestaurantDao = userRestaurantDao;
    }

    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void setUserGroupRestaurantDao(RestaurantDao<UserGroup> userGroupRestaurantDao) {
        this.userGroupRestaurantDao = userGroupRestaurantDao;
    }
}
