package ua.in.dris4ecoder.controllers.businessControllers;

import ua.in.dris4ecoder.model.businessObjects.User;
import ua.in.dris4ecoder.model.businessObjects.UserGroup;
import ua.in.dris4ecoder.model.businessObjects.UserRoles;

import java.util.List;
import java.util.Map;

/**
 * Created by Alex Korneyko on 01.11.2016 20:10.
 */
public interface GroupsRegistrationController {

    void validateUserGroup(UserGroup userGroup);

    void addUserGroup(UserGroup userGroup);

    void addUserGroup(String groupName, List<UserRoles> groupAuthorities);

    UserGroup findUserGroup(int userGroupId);

    UserGroup findUserGroup(UserGroup userGroup);

    List<UserGroup> findUserGroup(String userGroupName);

    List<UserGroup> getAllGroups();

    void editUserGroup(UserGroup userGroup);

    void removeUserGroup(int userGroupId);

    void removeUserGroup(UserGroup userGroup);

    void removeUserGroup(String userGroupName);
}
