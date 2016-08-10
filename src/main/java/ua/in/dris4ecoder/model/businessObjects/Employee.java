package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import ua.in.dris4ecoder.controllers.StaffController;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcEmployeePostsDao;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Alex Korneyko on 29.07.2016 18:15.
 */
public class Employee implements BusinessObject {

    public int id;
    public SimpleStringProperty lastName = new SimpleStringProperty();
    public SimpleStringProperty firstName = new SimpleStringProperty();
    public SimpleStringProperty telephone = new SimpleStringProperty();
    private LocalDate dateOfBirth;
    public SimpleStringProperty dateOfBirthPrpt = new SimpleStringProperty();

    public EmployeePost employeePost;
    public SimpleDoubleProperty salary = new SimpleDoubleProperty();

    private JdbcEmployeePostsDao jdbcEmployeePostsDao = new JdbcEmployeePostsDao();

    public Employee(){}

    public Employee(String lastName, String firstName, EmployeePost employeePost) {
        this.lastName.set(lastName);
        this.firstName.set(firstName);
        this.employeePost = employeePost;
    }

    public Employee(int id, String lastName, String firstName, LocalDate dateOfBirth, String telephone, EmployeePost employeePost, double salary) {

        this(lastName, firstName, employeePost);
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.dateOfBirthPrpt.set(String.valueOf(dateOfBirth));
        this.telephone.set(telephone);
        this.salary.setValue(salary);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.dateOfBirthPrpt.setValue(String.valueOf(this.dateOfBirth));
    }

    public String getDateOfBirthPrpt() {
        return dateOfBirthPrpt.get();
    }

    public SimpleStringProperty dateOfBirthPrptProperty() {
        return dateOfBirthPrpt;
    }

    public String getTelephone() {
        return telephone.get();
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public EmployeePost getEmployeePost() {
        return employeePost;
    }

    public void setEmployeePost(EmployeePost employeePost) {
        this.employeePost = employeePost;
    }

    public double getSalary() {
        return salary.get();
    }

    public void setSalary(double salary) {
        this.salary.setValue(salary);
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

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public SimpleStringProperty telephoneProperty() {
        return telephone;
    }

    public SimpleDoubleProperty salaryProperty() {
        return salary;
    }
}
