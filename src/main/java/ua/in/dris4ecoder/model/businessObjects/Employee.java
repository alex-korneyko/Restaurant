package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Alex Korneyko on 29.07.2016 18:15.
 */
@Entity
@Table(name = "service.employees")
public class Employee implements BusinessObject {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "telephone")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private EmployeePost employeePost;

    @Column(name = "salary")
    private double salary;

    @OneToOne
    @JoinColumn(name = "user_login")
    private UserImpl user;

    @Transient
    private SimpleIntegerProperty idProp = new SimpleIntegerProperty();
    @Transient
    private SimpleStringProperty lastNameProp = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty firstNameProp = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty phoneNumberProp = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty dateOfBirthProp = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty employeePostProp = new SimpleStringProperty();
    @Transient
    private SimpleDoubleProperty salaryProp = new SimpleDoubleProperty();

    public Employee(){}

    public Employee(String lastName, String firstName, EmployeePost employeePost, UserImpl user) {
        this.lastName = lastName;
        this.lastNameProp.set(lastName);
        this.firstName = firstName;
        this.firstNameProp.set(firstName);
        this.employeePost = employeePost;
        this.employeePostProp.set(employeePost.toString());
        this.user = user;
    }

    public Employee(int id, String lastName, String firstName, LocalDate dateOfBirth, String telephone,
                    EmployeePost employeePost, double salary, UserImpl user) {

        this(lastName, firstName, employeePost, user);
        this.id = id;
        this.idProp.set(id);
        this.dateOfBirth = dateOfBirth;
        this.dateOfBirthProp.set(dateOfBirth.toString());
        this.phoneNumber = telephone;
        this.phoneNumberProp.set(telephone);
        this.salary = salary;
        this.salaryProp.setValue(salary);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.idProp.set(id);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.lastNameProp.set(lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.firstNameProp.set(firstName);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.dateOfBirthProp.set(dateOfBirth.toString());
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String telephone) {
        this.phoneNumber = telephone;
        this.phoneNumberProp.set(telephone);
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
        this.salaryProp.setValue(salary);
    }

    public UserImpl getUser() {
        return user;
    }

    public void setUser(UserImpl user) {
        this.user = user;

        if (user.getUserName() == null)
            this.user.setUserName(this.firstName);

        if (user.getUserSurName() == null)
            this.user.setUserSurName(this.lastName);
    }

    //-----------------------------------

    public SimpleIntegerProperty idPropProperty() {
        idProp.set(id);
        return idProp;
    }

    public SimpleStringProperty lastNamePropProperty() {
        lastNameProp.set(lastName);
        return lastNameProp;
    }

    public SimpleStringProperty firstNamePropProperty() {
        firstNameProp.set(firstName);
        return firstNameProp;
    }

    public SimpleStringProperty phoneNumberPropProperty() {
        phoneNumberProp.set(phoneNumber);
        return phoneNumberProp;
    }

    public SimpleStringProperty dateOfBirthPropProperty() {
        dateOfBirthProp.set(dateOfBirth.toString());
        return dateOfBirthProp;
    }

    public SimpleStringProperty employeePostPropProperty() {
        employeePostProp.set(employeePost.toString());
        return employeePostProp;
    }

    public SimpleDoubleProperty salaryPropProperty() {
        salaryProp.setValue(salary);
        return salaryProp;
    }


    //-----------------------------------

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastNameProp='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", telephone='" + phoneNumber + '\'' +
                ", postId=" + employeePost.getPostName() +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(employee.phoneNumber) : employee.phoneNumber != null)
            return false;
        return dateOfBirth != null ? dateOfBirth.equals(employee.dateOfBirth) : employee.dateOfBirth == null;

    }

    @Override
    public int hashCode() {
        int result = lastName != null ? lastName.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        return result;
    }
}
