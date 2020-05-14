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
    private final TemplateEngine engine;
    DatabaseConnection db=new DatabaseConnection();
    Connection con=db.connect();
    DAO<User> daoUser=new UserDAO(con);

    public UserService(TemplateEngine engine) throws SQLException {
        this.engine = engine;
    }

    public List<User> getAllUsers(){
       return daoUser.getAll();
    }

    public User getById(int id){
        return daoUser.get(id);
    }
}
