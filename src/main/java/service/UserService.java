package service;

import dao.DAO;
import dao.LikesDAO;
import dao.UserDAO;
import db.DatabaseConnection;
import entity.Like;
import entity.User;
import util.TemplateEngine;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {


   private UserDAO daoUser=new UserDAO();

    public UserService(UserDAO daoUser) throws SQLException {
        this.daoUser=daoUser;
    }

    public int getMaxId() {
        return daoUser.getMaxId();
    }

    public List<User> getAllUsers(){
       return daoUser.getAll();
    }

    public User getById(int id){
        return daoUser.get(id);
    }

    public boolean checkUser(String login,String password){
        User result = daoUser.getByLoginAndPassword(login,password);
        return result != null && result.getPassword().equals(password) && result.getLogin().equals(login);
    }

    public User checkUserByLogin(User user){
       return daoUser.getUserByLogin(user);
    }

    public int getUserId(String login,String password) {
       return daoUser.getUserId(login, password);
    }

    public boolean addUser(User user){
        return daoUser.add(user);
    }

}
