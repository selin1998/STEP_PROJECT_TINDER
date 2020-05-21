package org.tinder.step.service;

import org.tinder.step.dao.DAO;
import org.tinder.step.dao.UserDAO;
import org.tinder.step.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserService {


    UserDAO daoUser = new UserDAO();


    public UserService() throws SQLException {
    }


    public List<User> getAllUsers() {
        return daoUser.getAll();
    }

    public List<Integer> getAllUserIds(int loggeduserId) {

        return daoUser.getAll().stream().map(u -> u.getUser_id()).filter(f -> f != loggeduserId).collect(Collectors.toList());
    }

    public User getById(int id) {
        return daoUser.get(id);
    }

//    public int getUserId(String login, String password) {
//        return getByLoginAndPassword(login, password).getUser_id();
//    }

    public boolean addUser(User user) {
        return daoUser.add(user);
    }

/*
    public boolean checkUser(String login, String password) {
        User result = getByLoginAndPassword(login, password);
        return result != null && result.getPassword().equals(password) && result.getLogin().equals(login);
    }
    public boolean checkUserByLogin(String login, String password) {
        return getByLoginAndPassword(login, password) != null;
    }
    private User getByLoginAndPassword(String login, String password) {
        Predicate<User> u1 = u -> u.getLogin().equals(login);
        Predicate<User> u2 = u -> u.getPassword().equals(password);
        List<User> user = daoUser.getAllBy(u1.and(u2)).stream().collect(Collectors.toList());
        return user.get(0);
    }
*/

    //checks when sign in
    public boolean checkUser(User user) {
        User result = daoUser.getByLoginAndPassword(user);
        return result != null && result.getPassword().equals(user.getPassword()) && result.getLogin().equals(user.getLogin());
    }

    //checks when sign up -> such user exist or no
    public boolean checkUserByLogin(User user) {
       return daoUser.getUserByLogin(user)!=null;
    }

    public int getUserId(String login, String password) {
        return daoUser.getUserId(login, password);
    }

}

