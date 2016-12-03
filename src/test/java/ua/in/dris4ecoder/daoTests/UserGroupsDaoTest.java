package ua.in.dris4ecoder.daoTests;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.in.dris4ecoder.model.businessObjects.UserGroup;
import ua.in.dris4ecoder.model.businessObjects.UserRole;
import ua.in.dris4ecoder.model.dao.RestaurantDao;
import ua.in.dris4ecoder.springTestConfigClasses.JpaTestConfig;
import ua.in.dris4ecoder.springTestConfigClasses.TestConfig;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by admin on 03.12.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, TestConfig.class})
@FixMethodOrder(MethodSorters.JVM)
@WebAppConfiguration
public class UserGroupsDaoTest {

    private static final String USERS = "TestUsers";
    private static final String ROLE_USER = "ROLE_TEST_USER";
    private static final String ROLE_SUPERUSER = "ROLE_TEST_SUPERUSER";
    private int id;

    @Autowired
    private RestaurantDao<UserGroup> userGroupRestaurantDao;

    @Autowired
    private RestaurantDao<UserRole> userRoleRestaurantDao;

    @Before
    public void addUserGroupTest() {

        userRoleRestaurantDao.addItem(new UserRole(ROLE_USER));
        userRoleRestaurantDao.addItem(new UserRole(ROLE_SUPERUSER));

        UserGroup userGroup = new UserGroup(USERS, Collections.singletonList(userRoleRestaurantDao.findItem(ROLE_USER)));

        id = userGroupRestaurantDao.addItem(userGroup);

        assertNotEquals(0, id);
    }

    @After
    public void removeUserGroupTest() {

        userGroupRestaurantDao.removeItemById(id);

        UserGroup userGroup = userGroupRestaurantDao.findItemById(id);

        userRoleRestaurantDao.removeItemByName(ROLE_USER);
        userRoleRestaurantDao.removeItemByName(ROLE_SUPERUSER);

        assertNull(userGroup);

    }

    @Test
    public void findUserGroupByIdTest() {

        UserGroup userGroup = userGroupRestaurantDao.findItemById(id);

        assertEquals(id, userGroup.getId());
    }

    @Test
    public void findUserGroupByNameTest() {

        UserGroup userGroup = userGroupRestaurantDao.findItem(USERS);

        assertNotNull(userGroup);
        assertEquals(id, userGroup.getId());
    }

    @Test
    public void editUserGroupTest() {

        UserGroup userGroup = userGroupRestaurantDao.findItemById(id);
        userGroup.setGroupName("SuperUsers");
        userGroup.setRoles(Collections.singletonList(userRoleRestaurantDao.findItem(ROLE_SUPERUSER)));

        userGroupRestaurantDao.editItem(id, userGroup);

        UserGroup userGroup2 = userGroupRestaurantDao.findItemById(id);

        assertEquals(userGroup.getGroupName(), userGroup2.getGroupName());
        assertEquals(userGroup.getRoles().size(), userGroup2.getRoles().size());

        List<String> expectedRoles = userGroup.getRoles().stream().map(UserRole::getRoleName).collect(Collectors.toList());
        List<String> actualRoles = userGroup2.getRoles().stream().map(UserRole::getRoleName).collect(Collectors.toList());

        assertEquals(expectedRoles, actualRoles);
    }
}
