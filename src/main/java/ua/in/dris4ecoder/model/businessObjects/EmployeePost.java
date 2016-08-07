package ua.in.dris4ecoder.model.businessObjects;

import java.util.List;
import java.util.Map;

/**
 * Created by Alex Korneyko on 29.07.2016 19:12.
 */
public class EmployeePost implements BusinessObject {

    public int id;

    public String postName;

    public EmployeePost(int id) {
        this.id = id;
    }

    public EmployeePost(String postName) {
        this.postName = postName;
    }

    public EmployeePost(int id, String postName) {
        this.id = id;
        this.postName = postName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  postName;
    }
}
