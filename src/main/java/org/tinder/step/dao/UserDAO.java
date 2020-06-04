package org.tinder.step.dao;

import lombok.extern.log4j.Log4j2;
import org.tinder.step.db.DatabaseConnection;
import org.tinder.step.entity.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static java.sql.Types.NULL;

@Log4j2
public class UserDAO implements DAO<User> {
    DatabaseConnection db = new DatabaseConnection();
    Connection con;

    public UserDAO() {

        this.con = db.connection();
    }

    @Override
    public Optional<User> get(int id) {
        String sql = "SELECT * FROM users WHERE user_id=? ";
        User user = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("user_id")
                        , rs.getString("login")
                        , rs.getString("password")
                        , rs.getString("name")
                        , rs.getString("surname")
                        , rs.getString("job")
                        , rs.getBytes("photoLink"));
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return Optional.ofNullable(user);
    }


    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        User user = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new User(rs.getInt("user_id")
                        , rs.getString("login")
                        , rs.getString("password")
                        , rs.getString("name")
                        , rs.getString("surname")
                        , rs.getString("job")
                        , rs.getBytes("photolink"));
                users.add(user);
            }


        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return users;
    }

    @Override
    public boolean add(User user) {
        boolean result = false;
        String sql = "INSERT INTO users (login,password,name,surname,job,photoLink,user_id) VALUES(?,?,?,?,?,?,(SELECT COALESCE(MAX(user_id),0) FROM users)+1)";


        try {

            PreparedStatement ps = con.prepareStatement(sql);
            InputStream inputStream = new ByteArrayInputStream(user.getPhotoLink());
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getJob());
            ps.setBinaryStream(6, inputStream);
            ps.execute();
            result = true;

        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;


    }

    @Override
    public boolean remove(User user) {
        String sql = "DELETE FROM users WHERE user_id=?";
        boolean result = false;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user.getUser_id());
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    //get user_id by login and password
    public int getUserId(String login, String psw) {
        String sql = "SELECT user_id FROM users WHERE login=? and password=?";
        int result = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, psw);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("user_id");
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    // get user by login and password
    public User getByLoginAndPassword(User user) {  //String login,String psw
        User result = null;
        String sql = "SELECT * FROM users WHERE login=? AND password=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = new User(rs.getInt("user_id")
                        , rs.getString("login")
                        , rs.getString("password")
                        , rs.getString("name")
                        , rs.getString("surname")
                        , rs.getString("job")
                        , rs.getBytes("photoLink"));
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return result;
    }

    public User getUserByLogin(User user) {
        String sql = "SELECT * FROM users WHERE login=?";
        User result = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getLogin());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String login = rs.getString("login");
                String password = rs.getString("password");
                byte[] photoLinks = rs.getBytes("photoLink");
                String job = rs.getString("job");

                result = new User(user_id, login, password, name, surname, job, photoLinks);

            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;
    }

}
