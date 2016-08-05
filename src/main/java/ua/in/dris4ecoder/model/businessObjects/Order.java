package ua.in.dris4ecoder.model.businessObjects;

import java.util.Date;

/**
 * Created by Alex Korneyko on 01.08.2016 20:15.
 */
public class Order {

    private Employee waiter;
    private int desk;
    private OrderDishStatus status;
    private Date dateOfCreation;

    public Order(Employee waiter, int desk) {
        this.waiter = waiter;
        this.desk = desk;

        status = OrderDishStatus.IN_QUEUE;
        dateOfCreation = new Date();
    }

    public Employee getWaiter() {
        return waiter;
    }

    public int getDesk() {
        return desk;
    }

    public OrderDishStatus getStatus() {
        return status;
    }

    public void setStatus(OrderDishStatus status) {
        this.status = status;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }
}
