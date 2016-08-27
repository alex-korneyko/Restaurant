package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by Alex Korneyko on 29.07.2016 19:12.
 */
public class EmployeePost implements BusinessObject {

    public SimpleIntegerProperty id = new SimpleIntegerProperty();

    public SimpleStringProperty postName = new SimpleStringProperty();

    public EmployeePost() {
    }

    public EmployeePost(int id) {
        this.id.setValue(id);
    }

    public EmployeePost(String postName) {
        this.postName.set(postName);
    }

    public EmployeePost(int id, String postName) {
        this(postName);
        this.id.set(id);
    }


    public String getPostName() {
        return postName.get();
    }

    public void setPostName(String postName) {
        this.postName.set(postName);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleStringProperty postNameProperty() {
        return postName;
    }

    @Override
    public String toString() {
        return  postName.get();
    }
}
