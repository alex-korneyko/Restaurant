package ua.in.dris4ecoder;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.in.dris4ecoder.model.businessServices.StaffService;
import ua.in.dris4ecoder.springTestConfigClasses.JpaTestConfig;
import ua.in.dris4ecoder.springTestConfigClasses.TestConfig;

/**
 * Created by admin on 30.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, TestConfig.class})
public class StaffServiceTest {

    @Autowired
    private StaffService staffService;



}
