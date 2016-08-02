package ua.in.dris4ecoder.controllers;


import ua.in.dris4ecoder.model.Employee;
import ua.in.dris4ecoder.model.EmployeePost;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.util.List;

/**
 * Created by Alex Korneyko on 28.07.2016 19:09.
 */
public class StaffController {

    private RestaurantDao<EmployeePost> employeePostsDao;
    private RestaurantDao<Employee> employeeDao;

    public void addEmployeePost(String name) {
        employeePostsDao.addItem(new EmployeePost(name));
    }

    public void removeEmployeePost(int id) {
        employeePostsDao.removeItemById(id);
    }

    public void editEmployeePost(int id, String newEmployeePostName) {
        employeePostsDao.editItem(id, new EmployeePost(newEmployeePostName));
    }

    public EmployeePost findEmployeePost(String name) {
        return employeePostsDao.findItem(name);
    }

    public EmployeePost findEmployeePostById(int id) {
        return employeePostsDao.findItemById(id);
    }

    public List<EmployeePost> getAllEmployeePosts() {
        return employeePostsDao.findAll();
    }




    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }







    public void setEmployeeDao(RestaurantDao<Employee> employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setEmployeePostsDao(RestaurantDao<EmployeePost> employeePostsDao) {
        this.employeePostsDao = employeePostsDao;
    }
}
