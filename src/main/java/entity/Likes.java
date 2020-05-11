package entity;

import java.util.ArrayList;
import java.util.List;

public class Likes {
    private String userLogin;
    private List<User> users;

    public Likes(String userLogin, ArrayList<User> users) {
        this.userLogin = userLogin;
        this.users = users;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
