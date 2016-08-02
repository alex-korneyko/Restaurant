package ua.in.dris4ecoder.model;

import java.util.Date;

/**
 * Created by Alex Korneyko on 01.08.2016 20:48.
 */
public class KitchenProcess {

    private Dish dish;
    private Employee cook;
    private Order order;
    private OrderDishStatus status;
    private Date dateOfCooked;

    public KitchenProcess(Dish dish, Employee cook, Order order) {
        this.dish = dish;
        this.cook = cook;
        this.order = order;
        status = OrderDishStatus.IN_QUEUE;
    }

    public Dish getDish() {
        return dish;
    }

    public Employee getCook() {
        return cook;
    }

    public Order getOrder() {
        return order;
    }

    public OrderDishStatus getStatus() {
        return status;
    }

    public void setStatus(OrderDishStatus status) {
        this.status = status;
    }

    public Date getDateOfCooked() {
        return dateOfCooked;
    }
}
