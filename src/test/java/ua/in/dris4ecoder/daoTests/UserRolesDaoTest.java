package ua.in.dris4ecoder.daoTests;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.in.dris4ecoder.model.businessObjects.UserRole;
import ua.in.dris4ecoder.model.dao.RestaurantDao;
import ua.in.dris4ecoder.springTestConfigClasses.JpaTestConfig;
import ua.in.dris4ecoder.springTestConfigClasses.TestConfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by admin on 02.12.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, TestConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
public class UserRolesDaoTest {

    public static final String ROLE_USER = "ROLE_USER" ;
    @Autowired
    private RestaurantDao<UserRole> userRoleRestaurantDao;

    @Test
    public void addUserRoleTest() {

        int role_user = userRoleRestaurantDao.addItem(new UserRole(ROLE_USER));

        assertEquals(1, role_user);
    }

    @Test
    public void findUserRoleTest() {

        UserRole userRole = userRoleRestaurantDao.findItem(ROLE_USER);

        assertEquals(ROLE_USER, userRole.getRoleName());
    }

    @Test
    public void removeUserRoleTest() {

        userRoleRestaurantDao.removeItemByName(ROLE_USER);

        assertNull(userRoleRestaurantDao.findItem(ROLE_USER));
    }
}
