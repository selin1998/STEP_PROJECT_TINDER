package org.tinder.step.service;

import org.tinder.step.dao.DAO;
import org.tinder.step.dao.UserDAO;
import org.tinder.step.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserService {


    UserDAO daoUser;

    public UserService() {
        daoUser = new UserDAO();

    }


    public List<Integer> getAllUserIds(int loggeduserId) {

        return daoUser.getAll().stream().map(u -> u.getUser_id()).filter(f -> f != loggeduserId).sorted().collect(Collectors.toList());
    }

    public Optional<User> getById(int id) {
        return daoUser.get(id);
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
        return daoUser.getUserByLogin(user) != null;
    }

    public int getUserId(String login, String password) {
        return daoUser.getUserId(login, password);
    }

}

