package ua.in.dris4ecoder.controllers;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.EmployeePost;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.util.List;

/**
 * Created by Alex Korneyko on 28.07.2016 19:09.
 */
public class StaffController {

    private RestaurantDao employeePostsDao;
    private RestaurantDao employeeDao;










    public void setEmployeeDao(RestaurantDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setEmployeePostsDao(RestaurantDao employeePostsDao) {
        this.employeePostsDao = employeePostsDao;
    }
}
