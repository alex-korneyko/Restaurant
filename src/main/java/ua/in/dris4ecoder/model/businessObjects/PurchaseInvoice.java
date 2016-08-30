package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;

/**
 * Created by Alex Korneyko on 30.08.2016 12:46.
 */
@Entity
@Table(name = "service.purchase_invoices")
@PrimaryKeyJoinColumn(name = "invoice_id", referencedColumnName = "id")
public class PurchaseInvoice extends Invoice {

    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    @Column(name = "amount_invoice")
    private double amountOfInvoice;

    @Transient
    private SimpleStringProperty supplierProp = new SimpleStringProperty();
    @Transient
    private SimpleDoubleProperty amountOfInvoiceProp = new SimpleDoubleProperty();

    public PurchaseInvoice() {
    }

    public PurchaseInvoice(Contractor contractor) {
        this.contractor = contractor;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        supplierProp.set(contractor.getContractorName());
        this.contractor = contractor;
    }

    public double getAmountOfInvoice() {
        return amountOfInvoice;
    }

    public void setAmountOfInvoice(double amountOfInvoice) {
        amountOfInvoiceProp.setValue(amountOfInvoice);
        this.amountOfInvoice = amountOfInvoice;
    }

    public SimpleStringProperty supplierPropProperty() {
        supplierProp.set(contractor.getContractorName());
        return supplierProp;
    }

    public SimpleDoubleProperty amountOfInvoicePropProperty() {
        amountOfInvoiceProp.set(amountOfInvoice);
        return amountOfInvoiceProp;
    }
}
