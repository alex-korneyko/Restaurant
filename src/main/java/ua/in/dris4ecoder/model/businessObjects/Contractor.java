package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Alex Korneyko on 30.08.2016 12:49.
 */
@Entity
@Table(name = "service.contractors")
public class Contractor {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Column(name = "contractor_name")
    private String contractorName;

    @Transient
    private SimpleIntegerProperty idProp = new SimpleIntegerProperty();
    @Transient
    private SimpleStringProperty contractorNameProp = new SimpleStringProperty();

    public Contractor() {
    }

    public Contractor(String contractorName) {
        this.contractorName = contractorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        idProp.set(id);
        this.id = id;
    }

    public String getContractorName() {
        return contractorName;
    }

    public String getNameWithHtmlQuot() {

        StringBuilder nameWithBackSlash = new StringBuilder();

        for (char c : contractorName.toCharArray()) {
            if (c =='"') {
                nameWithBackSlash.append("&quot;");
                continue;
            }
                nameWithBackSlash.append(c);
        }

        return nameWithBackSlash.toString();
    }

    public void setContractorName(String contractorName) {
        contractorNameProp.set(contractorName);
        this.contractorName = contractorName;
    }

    public SimpleIntegerProperty idPropProperty() {
        idProp.set(id);
        return idProp;
    }

    public SimpleStringProperty contractorNamePropProperty() {
        contractorNameProp.set(contractorName);
        return contractorNameProp;
    }

    @Override
    public String toString() {
        return "Contractor{" +
                "id=" + id +
                ", contractorName='" + contractorName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contractor)) return false;

        Contractor contractor = (Contractor) o;

        return contractorName != null ? contractorName.equals(contractor.contractorName) : contractor.contractorName == null;

    }

    @Override
    public int hashCode() {
        return contractorName != null ? contractorName.hashCode() : 0;
    }
}
