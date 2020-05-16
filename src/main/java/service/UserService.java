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


    DAO<User> daoUser=new UserDAO();

    public UserService() throws SQLException {
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
}
