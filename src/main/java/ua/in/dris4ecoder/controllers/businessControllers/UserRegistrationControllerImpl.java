package ua.in.dris4ecoder.controllers.businessControllers;

import org.hibernate.SessionFactory;
import ua.in.dris4ecoder.model.businessObjects.User;
import ua.in.dris4ecoder.model.businessObjects.UserImpl;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.util.Map;

/**
 * Created by Alex Korneyko on 27.10.2016 19:01.
 */
public class UserRegistrationControllerImpl implements UserRegistrationController {

    private RestaurantDao<User> userRestaurantDao;

    @Override
    public void validateUser(User user) {

    }

    @Override
    public void addUser(User user) {

        userRestaurantDao.addItem(user);
    }

    @Override
    public void addUser(Map<String, String> userData) {

        userRestaurantDao.addItem(new UserImpl(userData));
    }

    @Override
    public void findUser(User user) {

    }

    @Override
    public void finUser(String userLogin) {

    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public void removeUser(String userLogin) {

    }

    public void setUserRestaurantDao(RestaurantDao<User> userRestaurantDao) {
        this.userRestaurantDao = userRestaurantDao;
    }
}
