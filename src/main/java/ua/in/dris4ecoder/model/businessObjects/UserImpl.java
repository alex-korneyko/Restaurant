package ua.in.dris4ecoder.model.businessObjects;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex Korneyko on 27.10.2016 18:57.
 */
@Entity
@Table(name = "security.users")
public class UserImpl implements User{

    @Column(name = "name")
    private String userName;

    @Column(name = "surname")
    private String userSurName;

    @Id
    @Column(name = "username")
    private String userLogin;

    @Column(name = "password")
    private String userPass;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.JOIN)
    @JoinTable(
            name = "security.group_members",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<UserGroup> userGroups;

    public UserImpl() {
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUserSurName() {
        return userSurName;
    }

    @Override
    public void setUserSurName(String userSurName) {
        this.userSurName = userSurName;
    }

    @Override
    public String getUserFullName() {

        return this.userName + " " + this.userSurName;
    }

    @Override
    public String getUserLogin() {
        return userLogin;
    }

    @Override
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String getUserPass() {
        return userPass;
    }

    @Override
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    @Override
    public String toString() {
        return "UserImpl{" +
                "userName='" + userName + '\'' +
                ", userSurName='" + userSurName + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", userPass='" + userPass + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
