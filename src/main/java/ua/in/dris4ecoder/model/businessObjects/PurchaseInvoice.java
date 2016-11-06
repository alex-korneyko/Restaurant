package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @Column(name = "invoice_id_from_contractor")
    private String invoiceIdFromContractor;

    @Transient
    private SimpleStringProperty contractorProp = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty invoiceIdFromContractorProp = new SimpleStringProperty();

    public PurchaseInvoice() {
    }

    public PurchaseInvoice(boolean autoPrice) {
        super(autoPrice);
    }

    public PurchaseInvoice(Contractor contractor) {
        this.contractor = contractor;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        contractorProp.set(contractor.getContractorName());
        this.contractor = contractor;
    }

    public String getInvoiceIdFromContractor() {
        return invoiceIdFromContractor;
    }

    public void setInvoiceIdFromContractor(String invoiceIdFromContractor) {
        invoiceIdFromContractorProp.set(invoiceIdFromContractor);
        this.invoiceIdFromContractor = invoiceIdFromContractor;
    }

    public SimpleStringProperty contractorPropProperty() {
        contractorProp.set(contractor.getContractorName());
        return contractorProp;
    }

    public SimpleStringProperty invoiceIdFromContractorPropProperty() {
        invoiceIdFromContractorProp.set(invoiceIdFromContractor);
        return invoiceIdFromContractorProp;
    }

    @Override
    public String toString() {
        return "PurchaseInvoice{" +
                "contractor=" + contractor +
                ", invoiceIdFromContractor='" + invoiceIdFromContractor + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseInvoice)) return false;
        if (!super.equals(o)) return false;

        PurchaseInvoice that = (PurchaseInvoice) o;

        if (contractor != null ? !contractor.equals(that.contractor) : that.contractor != null) return false;
        return invoiceIdFromContractor != null ? invoiceIdFromContractor.equals(that.invoiceIdFromContractor) : that.invoiceIdFromContractor == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (contractor != null ? contractor.hashCode() : 0);
        result = 31 * result + (invoiceIdFromContractor != null ? invoiceIdFromContractor.hashCode() : 0);
        return result;
    }
}
