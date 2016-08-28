package ua.in.dris4ecoder.controllers.businessControllers;


import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alex Korneyko on 28.07.2016 19:09.
 */
public class StaffController implements BusinessController {

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

    public List<EmployeePost> findEmployeePost(String name) {
        return employeePostsDao.findItem(name);
    }

    public EmployeePost findEmployeePostById(int id) {
        return employeePostsDao.findItemById(id);
    }

    public List<EmployeePost> getAllEmployeePosts() {
        return employeePostsDao.findAll();
    }


    public void addEmployee(String lastName, String firstName, int postId) {
        employeeDao.addItem(new Employee(lastName, firstName, Main.getStaffController().findEmployeePostById(postId)));
    }

    public void addEmployee(Employee employee) {
        employeeDao.addItem(employee);
    }

    public void removeEmployee(int id) {
        employeeDao.removeItemById(id);
    }

    public void editEmployee(int id, Employee changedEmployee) {
        employeeDao.editItem(id, changedEmployee);
    }

    public List<Employee> findEmployeeByName(String name) {
        return employeeDao.findItem(name);
    }

    public Employee findEmployeeById(int id) {
        return employeeDao.findItemById(id);
    }

    public List<Employee> findEmployeeByDateRange(LocalDate start, LocalDate end) {
        return employeeDao.findItem(start, end);
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
