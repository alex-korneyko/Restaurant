package ua.in.dris4ecoder.model.businessServices;

import ua.in.dris4ecoder.model.businessObjects.UserGroup;
import ua.in.dris4ecoder.model.businessObjects.UserRoles;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.util.List;

/**
 * Created by Alex Korneyko on 01.11.2016 21:07.
 */
public class GroupsRegistrationServiceImpl implements GroupsRegistrationService {

    private RestaurantDao<UserGroup> userGroupRestaurantDao;

    @Override
    public void validateUserGroup(UserGroup userGroup) {

    }

    @Override
    public void addUserGroup(UserGroup userGroup) {

    }

    @Override
    public void addUserGroup(String groupName, List<UserRoles> groupAuthorities) {

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
    public List<UserGroup> findUserGroup(String userGroupName) {
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

    }

    public void setUserGroupRestaurantDao(RestaurantDao<UserGroup> userGroupRestaurantDao) {
        this.userGroupRestaurantDao = userGroupRestaurantDao;
    }
}
