package ua.in.dris4ecoder.model.businessObjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Alex Korneyko on 14.10.2016 18:48.
 */
@Entity
@Table(name = "security.roles")
public class UserRole {

    @Id
    @Column(name = "authority_role")
    private String roleName;

    public UserRole() {
    }

    public UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}
