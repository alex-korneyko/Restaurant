package ua.in.dris4ecoder.model.businessObjects;

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
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Transient
    private SimpleStringProperty supplierProp = new SimpleStringProperty();

    public PurchaseInvoice() {
    }

    public PurchaseInvoice(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        supplierProp.set(supplier.getSupplierName());
        this.supplier = supplier;
    }

    public SimpleStringProperty supplierPropProperty() {
        supplierProp.set(supplier.getSupplierName());
        return supplierProp;
    }
}