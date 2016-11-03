package ua.in.dris4ecoder.controllers.businessControllers;

import org.hibernate.SessionFactory;
import ua.in.dris4ecoder.model.businessObjects.User;

import java.util.Map;

/**
 * Created by Alex Korneyko on 27.10.2016 18:33.
 */
public interface UserRegistrationController{

    void validateUser(User user);

    void addUser(User user);

    void addUser(Map<String, String> userData);

    void editUser(User user);

    User findUser(User user);

    User findUser(String userLogin);

    void removeUser(User user);

    void removeUser(String userLogin);
}
