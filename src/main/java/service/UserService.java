package service;

import dao.UserDAO;
import entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {


    private UserDAO daoUser = new UserDAO();

    public UserService(UserDAO daoUser) throws SQLException {
        this.daoUser = daoUser;
    }

    public int getMaxId() {
        return daoUser.getMaxId();
    }

    public List<User> getAllUsers() {
        return daoUser.getAll();
    }

    public User getById(int id) {
        return daoUser.get(id);
    }

    //checks when sign in
    public boolean checkUser(String login, String password) {
        User result = daoUser.getByLoginAndPassword(login, password);
        return result != null && result.getPassword().equals(password) && result.getLogin().equals(login);
    }

    //checks when sign up -> such user exist or no
    public boolean checkUserByLogin(User user) {
        return daoUser.getUserByLogin(user) != null;
    }

    public int getUserId(String login, String password) {
        return daoUser.getUserId(login, password);
    }

    public boolean addUser(User user) {
        return daoUser.add(user);
    }

}
