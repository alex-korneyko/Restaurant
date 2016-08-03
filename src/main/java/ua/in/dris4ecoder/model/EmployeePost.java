package ua.in.dris4ecoder.model;

/**
 * Created by Alex Korneyko on 29.07.2016 19:12.
 */
public class EmployeePost {

    private int id;

    private String postName;

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
        return id + ".\t" + postName;
    }
}
