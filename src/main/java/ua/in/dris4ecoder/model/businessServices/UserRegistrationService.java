package ua.in.dris4ecoder.model.businessServices;

import ua.in.dris4ecoder.model.businessObjects.UserGroup;
import ua.in.dris4ecoder.model.businessObjects.UserImpl;
import ua.in.dris4ecoder.model.businessObjects.UserRole;

import java.util.List;
import java.util.Map;

/**
 * Created by Alex Korneyko on 27.10.2016 18:33.
 */
public interface UserRegistrationService {

    UserRole addUserRole(String userRole);

    UserRole addUserRole(UserRole userRole);

    UserRole findUserRole(String roleName);

    void editUserRole(UserRole changedUserRole);

    void removeUserRole(UserRole userRole);

    void removeUserRole(String userRoleName);

    void validateUserGroup(UserGroup userGroup);

    void addUserGroup(UserGroup userGroup);

    UserGroup addUserGroup(String groupName, List<UserRole> groupAuthorities);

    UserGroup findUserGroup(int userGroupId);

    UserGroup findUserGroup(UserGroup userGroup);

    UserGroup findUserGroup(String userGroupName);

    List<UserGroup> getAllGroups();

    void editUserGroup(UserGroup userGroup);

    void removeUserGroup(int userGroupId);

    void removeUserGroup(UserGroup userGroup);

    void removeUserGroup(String userGroupName);

    void validateUser(UserImpl user);

    UserImpl addUser(UserImpl user);

    void addUser(Map<String, String> userData);

    void editUser(UserImpl user);

    UserImpl findUser(int id);

    UserImpl findUser(UserImpl user);

    UserImpl findUser(String userLogin);

    void removeUser(UserImpl user);

    void removeUser(String userLogin);

    List<UserImpl> getAllUsers();
}
