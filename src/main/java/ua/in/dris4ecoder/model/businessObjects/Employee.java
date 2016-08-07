package ua.in.dris4ecoder.model.businessObjects;

import ua.in.dris4ecoder.controllers.StaffController;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcEmployeePostsDao;

import java.util.Date;

/**
 * Created by Alex Korneyko on 29.07.2016 18:15.
 */
public class Employee implements BusinessObject {

    public int id;
    public String lastName;
    public String firstName;
    public String telephone;
    public Date dateOfBirth;
    public EmployeePost employeePost;
    public double salary;

    private JdbcEmployeePostsDao jdbcEmployeePostsDao = new JdbcEmployeePostsDao();

    public Employee(String lastName, String firstName, EmployeePost employeePost) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.employeePost = employeePost;
    }

    public Employee(int id, String lastName, String firstName, Date dateOfBirth, String telephone, EmployeePost employeePost, double salary) {

        this(lastName, firstName, employeePost);
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.telephone = telephone;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public EmployeePost getEmployeePost() {
        return employeePost;
    }

    public void setEmployeePost(EmployeePost employeePost) {
        this.employeePost = employeePost;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", telephone='" + telephone + '\'' +
                ", postId=" + employeePost.getPostName() +
                ", salary=" + salary +
                '}';
    }
}
