package ua.in.dris4ecoder.model.businessObjects;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Alex Korneyko on 14.10.2016 18:57.
 */
@Entity
@Table(name = "security.groups")
public class UserGroup {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "group_name")
    private String groupName;

    @ManyToMany(cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.JOIN)
    @JoinTable(
            name = "security.group_authorities",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "authority")
    )
    private List<UserRoles> roles;

    public UserGroup() {
    }

    public UserGroup(String groupName) {
        this.groupName = groupName;
    }

    public UserGroup(String groupName, List<UserRoles> roles) {
        this.groupName = groupName;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "groupName='" + groupName + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGroup)) return false;

        UserGroup userGroup = (UserGroup) o;

        return groupName != null ? groupName.equals(userGroup.groupName) : userGroup.groupName == null;

    }

    @Override
    public int hashCode() {
        return groupName != null ? groupName.hashCode() : 0;
    }
}
