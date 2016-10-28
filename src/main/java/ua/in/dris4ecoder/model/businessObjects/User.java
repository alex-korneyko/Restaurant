package ua.in.dris4ecoder.model.businessObjects;

import java.util.List;

/**
 * Created by Alex Korneyko on 27.10.2016 18:56.
 */
public interface User {

    String getUserName();

    void setUserName(String userName);

    String getUserSurName();

    void setUserSurName(String userSurName);

    String getUserLogin();

    void setUserLogin(String userLogin);

    String getUserPass();

    void setUserPass(String userPass);

    boolean isEnabled();

    void setEnabled(boolean enabled);

    List<UserGroup> getUserGroups();

    public void setUserGroups(List<UserGroup> userGroups);
}
