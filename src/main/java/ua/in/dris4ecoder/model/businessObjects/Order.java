package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex Korneyko on 01.08.2016 20:15.
 */
@Entity
@Table(name = "service.orders")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "desk")
    private int desk;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderDishStatus status;

    @Column(name = "order_date")
    private LocalDate dateOfCreation;

    @ManyToMany
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "service.order_composition",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishes;

    @Transient
    private SimpleIntegerProperty idProp = new SimpleIntegerProperty();
    @Transient
    private SimpleStringProperty employeeNameProp = new SimpleStringProperty();
    @Transient
    private SimpleIntegerProperty deskProp = new SimpleIntegerProperty();
    @Transient
    private SimpleStringProperty statusProp = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty dateProp = new SimpleStringProperty();

    public Order() {
    }

    public Order(int id) {
        this.id = id;
        if(id == 0) {
            this.employee = new Employee();
        }
    }

    public Order(Employee employee, int desk) {
        this.employee = employee;
        this.desk = desk;

        status = OrderDishStatus.IN_QUEUE;
        dateOfCreation = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        idProp.set(id);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        employeeNameProp.set(employee.getFirstName() + " " + employee.getLastName());
    }

    public int getDesk() {
        return desk;

    }

    public void setDesk(int desk) {
        this.desk = desk;
        deskProp.set(desk);
    }

    public OrderDishStatus getStatus() {
        return status;
    }

    public void setStatus(OrderDishStatus status) {
        this.status = status;
        statusProp.set(status.toString());
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
        dateProp.set(dateOfCreation.toString());
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    //------------------------------------


    public SimpleIntegerProperty idPropProperty() {
        idProp.set(id);
        return idProp;
    }

    public SimpleStringProperty employeeNamePropProperty() {
        employeeNameProp.set(employee.getFirstName() + " " + employee.getLastName());
        return employeeNameProp;
    }

    public SimpleIntegerProperty deskPropProperty() {
        deskProp.set(desk);
        return deskProp;
    }

    public SimpleStringProperty statusPropProperty() {
        statusProp.set(status.toString());
        return statusProp;
    }

    public SimpleStringProperty datePropProperty() {
        dateProp.set(dateOfCreation.toString());
        return dateProp;
    }

    //----------------------------------------


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", employee=" + employee +
                ", desk=" + desk +
                ", status=" + status +
                ", dateOfCreation=" + dateOfCreation +
                ", dishes=" + dishes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (desk != order.desk) return false;
        if (employee != null ? !employee.equals(order.employee) : order.employee != null) return false;
        if (dateOfCreation != null ? !dateOfCreation.equals(order.dateOfCreation) : order.dateOfCreation != null)
            return false;
        return dishes != null ? dishes.equals(order.dishes) : order.dishes == null;

    }

    @Override
    public int hashCode() {
        int result = employee != null ? employee.hashCode() : 0;
        result = 31 * result + desk;
        result = 31 * result + (dateOfCreation != null ? dateOfCreation.hashCode() : 0);
        result = 31 * result + (dishes != null ? dishes.hashCode() : 0);
        return result;
    }
}
