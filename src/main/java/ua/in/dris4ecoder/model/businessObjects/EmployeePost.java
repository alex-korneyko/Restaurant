package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex Korneyko on 29.07.2016 19:12.
 */
@Entity
@Table(name = "service.employee_posts")
public class EmployeePost implements BusinessObject {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Column(name = "post_name")
    private String postName;

    @Transient
    private SimpleIntegerProperty idProp = new SimpleIntegerProperty();

    @Transient
    private SimpleStringProperty postNameProp = new SimpleStringProperty();

    public EmployeePost() {
    }

    public EmployeePost(String postName) {
        this.postName = postName;
        this.postNameProp.set(postName);
    }

    public EmployeePost(int id, String postName) {
        this(postName);
        this.id = id;
        this.idProp.set(id);
    }


    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
        this.postNameProp.set(postName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.idProp.set(id);
    }

    public SimpleIntegerProperty idPropProperty() {
        idProp.set(this.id);
        return idProp;
    }

    public SimpleStringProperty postNamePropProperty() {
        postNameProp.set(this.postName);
        return postNameProp;
    }

    @Override
    public String toString() {
        return  postName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeePost)) return false;

        EmployeePost that = (EmployeePost) o;

        return postName != null ? postName.equals(that.postName) : that.postName == null;

    }

    @Override
    public int hashCode() {
        return postName != null ? postName.hashCode() : 0;
    }
}
