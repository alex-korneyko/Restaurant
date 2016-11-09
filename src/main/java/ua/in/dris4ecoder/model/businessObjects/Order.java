package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    private LocalDateTime dateOfCreation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserImpl orderOwner;

    @ManyToMany
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "service.order_composition",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishes = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "service.order_dish_counts")
    @MapKeyJoinColumn(name = "dish_id")
    @Column(name = "dish_count")
    @Fetch(FetchMode.JOIN)
    private Map<Dish, Integer> dishesCount = new HashMap<>();

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
        this.status = OrderDishStatus.EDITING;
        dateOfCreation = LocalDateTime.now();
    }

    public Order(Employee employee) {
        this();
        this.employee = employee;
    }

    public Order(int id) {
        this();
        this.id = id;
        if(id == 0) {
            this.employee = new Employee();
        }
    }

    public Order(Employee employee, int desk) {
        this(employee);
        this.desk = desk;
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

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
        dateProp.set(dateOfCreation.toString());
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {

        dishes.forEach(this::addDish);
    }

    public UserImpl getOrderOwner() {
        return orderOwner;
    }

    public void setOrderOwner(UserImpl orderOwner) {
        this.orderOwner = orderOwner;
    }

    public Map<Dish, Integer> getDishesCount() {
        return dishesCount;
    }

    public void setDishesCount(Map<Dish, Integer> dishesCount) {
        this.dishesCount = dishesCount;
    }

    public void addDish(Dish dish) {

        if (dishes.contains(dish)) {
            dishesCount.put(dish, dishesCount.get(dish) + 1);
        } else {
            dishes.add(dish);
            dishesCount.put(dish, 1);
        }
    }

    public void removeDish(Dish dish) {

        if (!dishes.contains(dish)) return;

        if (dishesCount.get(dish) > 1) {
            dishesCount.put(dish, dishesCount.get(dish) - 1);
        } else {
            dishes.remove(dish);
            dishesCount.remove(dish);
        }
    }

    public int getDishCount(Dish dish) {

        if (!dishesCount.containsKey(dish)) return 0;

        return dishesCount.get(dish);
    }

    public double getOrderCost() {

        return dishes.stream().mapToDouble(dish -> dish.getPrice().doubleValue() * dishesCount.get(dish)).sum();
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
