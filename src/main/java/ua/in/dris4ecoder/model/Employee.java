package ua.in.dris4ecoder.model;

import java.util.Date;

/**
 * Created by Alex Korneyko on 29.07.2016 18:15.
 */
public class Employee {

    private int id;
    private String lastName;
    private String firstName;
    private Date dateOfBirth;
    private String telephone;
    private int postId;
    private double salary;

    public Employee(String lastName, String firstName, int postId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.postId = postId;
    }

    public Employee(int id, String lastName, String firstName, Date dateOfBirth, String telephone, int postId, double salary) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.telephone = telephone;
        this.postId = postId;
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
