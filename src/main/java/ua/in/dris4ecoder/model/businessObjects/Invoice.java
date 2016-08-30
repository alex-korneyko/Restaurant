package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alex Korneyko on 30.08.2016 11:58.
 */
@Entity
@Table(name = "service.invoices")
public abstract class Invoice implements BusinessObject {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Column(name = "date_of_waybill")
    private LocalDate invoiceDate;

    @ManyToMany
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "service.invoices_compositions",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    @Transient
    private SimpleIntegerProperty idProp = new SimpleIntegerProperty();
    @Transient
    private SimpleStringProperty invoiceDateProp = new SimpleStringProperty();

    public Invoice() {
        invoiceDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        idProp.set(id);
        this.id = id;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        invoiceDateProp.set(invoiceDate.toString());
        this.invoiceDate = invoiceDate;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public SimpleIntegerProperty idPropProperty() {
        idProp.set(id);
        return idProp;
    }

    public SimpleStringProperty invoiceDatePropProperty() {
        invoiceDateProp.set(invoiceDate.toString());
        return invoiceDateProp;
    }
}
