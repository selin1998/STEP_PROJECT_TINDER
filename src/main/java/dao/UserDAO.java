package dao;

import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {
    private Connection con;

    public UserDAO(Connection con) {
        this.con = con;
    }

    @Override
    public User get(int id) {
        String sql="SELECT * FROM users WHERE user_id=? ";
        User user=null;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery(sql);
            user=new User(rs.getString("login")
                    , rs.getString("password")
                    , rs.getString("firstname")
                    , rs.getString("lastname")
                    , rs.getString("job")
                    , rs.getString("photolink"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        String sql="SELECT * FROM users";
        User user=null;
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery(sql);
            while(rs.next()){
                user=new User(rs.getString("login")
                        , rs.getString("password")
                        , rs.getString("firstname")
                        , rs.getString("lastname")
                        , rs.getString("job")
                        , rs.getString("photolink"));
                users.add(user);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean add(User user) {
        boolean result=false;
        String sql="INSERT INTO users (login,password,name,surname,job,photoLink) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,user.getLogin());
            ps.setString(2,user.getSurname());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getName());
            ps.setString(5,user.getJob());
            ps.setString(6,user.getPhotoUrl());
            ps.executeUpdate();
            result=true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean remove(int id) {
        String sql="DELETE FROM users WHERE user_id=?";
        boolean result=false;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            result=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
