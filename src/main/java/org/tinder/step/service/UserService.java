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
    DAO<User> dao=new UserDAO();

    public UserService() throws SQLException {
    }


    public List<User> getAllUsers() {
        return daoUser.getAll();
    }

    public List<Integer> getAllUserIds(int loggeduserId) {

        return dao.getAll().stream().map(u -> u.getUser_id()).filter(f -> f != loggeduserId).sorted().collect(Collectors.toList());
    }

    public User getById(int id) {
        return dao.get(id).get();
    }

    public boolean addUser(User user) {
        return daoUser.add(user);
    }

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

