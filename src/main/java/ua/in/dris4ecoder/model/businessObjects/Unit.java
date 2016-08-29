package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Alex Korneyko on 29.08.2016 18:05.
 */
@Entity
@Table(name = "service.units")
public class Unit implements BusinessObject {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Column(name = "unit_name")
    private String unitName;

    @Transient
    private SimpleIntegerProperty idProp = new SimpleIntegerProperty();
    @Transient
    private SimpleStringProperty unitNameProp = new SimpleStringProperty();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        idProp.set(id);
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        unitNameProp.set(unitName);
        this.unitName = unitName;
    }

    public SimpleIntegerProperty idPropProperty() {
        idProp.set(id);
        return idProp;
    }

    public SimpleStringProperty unitNamePropProperty() {
        unitNameProp.set(unitName);
        return unitNameProp;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "unitName='" + unitName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;

        Unit unit = (Unit) o;

        return unitName != null ? unitName.equals(unit.unitName) : unit.unitName == null;

    }

    @Override
    public int hashCode() {
        return unitName != null ? unitName.hashCode() : 0;
    }
}
