package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;

/**
 * Created by Alex Korneyko on 30.08.2016 13:00.
 */
@Entity
@Table(name = "service.sales_invoices")
@PrimaryKeyJoinColumn(name = "invoice_id", referencedColumnName = "id")
public class SalesInvoice extends Invoice {

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Transient
    private SimpleIntegerProperty orderIdProp = new SimpleIntegerProperty();
    @Transient
    private SimpleStringProperty employeeProp = new SimpleStringProperty();

    public SalesInvoice() {
    }

    public SalesInvoice(Order order, Employee employee) {
        this.order = order;
        this.employee = employee;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        orderIdProp.set(order.getId());
        this.order = order;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        employeeProp.set(employee.getFirstName() + " " + employee.getLastName());
        this.employee = employee;
    }

    public SimpleIntegerProperty orderIdPropProperty() {
        orderIdProp.set(order.getId());
        return orderIdProp;
    }

    public SimpleStringProperty employeePropProperty() {
        employeeProp.set(employee.getFirstName() + " " + employee.getLastName());
        return employeeProp;
    }
}
