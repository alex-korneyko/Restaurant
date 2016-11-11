package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.util.Map;

/**
 * Created by Alex Korneyko on 30.08.2016 13:00.
 */
@Entity
@Table(name = "service.sales_invoices")
@PrimaryKeyJoinColumn(name = "invoice_id", referencedColumnName = "id")
public class SalesInvoice extends Invoice {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    @Transient
    private SimpleIntegerProperty orderIdProp = new SimpleIntegerProperty();
    @Transient
    private SimpleStringProperty contractorProp = new SimpleStringProperty();

    public SalesInvoice() {
    }

    public SalesInvoice(boolean autoPrice) {
        super(autoPrice);
    }

    public SalesInvoice(boolean autoPrice, Order order) {
        this(autoPrice);
        fillInvoice(order);
    }

    private void fillInvoice(Order order) {

        Map<Dish, Integer> dishesCount = order.getDishesCount();

        for(Dish dish: dishesCount.keySet()) {
            for(Ingredient ingredient: dish.getIngredients()) {
                for (int i = dishesCount.get(dish); i > 0; i--) {
                    addIngredient(dish.getIngredientWithParamsInDish(ingredient));
                }
            }
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        orderIdProp.set(order.getId());
        this.order = order;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        contractorProp.set(contractor.getContractorName());
        this.contractor = contractor;
    }

    public SimpleIntegerProperty orderIdPropProperty() {
        orderIdProp.set(order != null ? order.getId() : 0);
        return orderIdProp;
    }

    public SimpleStringProperty contractorPropProperty() {
        contractorProp.set(contractor.getContractorName());
        return contractorProp;
    }

    @Override
    public String toString() {
        return "SalesInvoice{" +
                "order=" + order +
                ", contractor=" + contractor +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalesInvoice)) return false;
        if (!super.equals(o)) return false;

        SalesInvoice that = (SalesInvoice) o;

        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        return contractor != null ? contractor.equals(that.contractor) : that.contractor == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (contractor != null ? contractor.hashCode() : 0);
        return result;
    }
}
