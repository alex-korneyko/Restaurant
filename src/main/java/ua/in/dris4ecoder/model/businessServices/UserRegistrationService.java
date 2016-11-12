package ua.in.dris4ecoder.model.businessServices;

import ua.in.dris4ecoder.model.businessObjects.UserImpl;

import java.util.List;
import java.util.Map;

/**
 * Created by Alex Korneyko on 27.10.2016 18:33.
 */
public interface UserRegistrationService {

    void validateUser(UserImpl user);

    void addUser(UserImpl user);

    void addUser(Map<String, String> userData);

    void editUser(UserImpl user);

    UserImpl findUser(UserImpl user);

    UserImpl findUser(String userLogin);

    void removeUser(UserImpl user);

    void removeUser(String userLogin);

    List<UserImpl> getAllUsers();
}
