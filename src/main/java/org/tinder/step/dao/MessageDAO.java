package org.tinder.step.dao;

import org.tinder.step.db.DatabaseConnection;
import org.tinder.step.entity.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MessageDAO implements DAO<Message> {
    DatabaseConnection db=new DatabaseConnection();
    Connection con;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public MessageDAO() throws SQLException {
        this.con = db.connection();
    }

    @Override
    public Message get(int id) {
        String sql="SELECT * FROM messages WHERE message_id=? ";
        Message msg=null;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();

            LocalDateTime dateTime = LocalDateTime.parse(rs.getString("time"), formatter);

            msg=new Message(rs.getInt("user_id_from")
                    , rs.getInt("user_id_to")
                    , rs.getString("message")
                    , dateTime);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msg;
    }



    @Override
    public List<Message> getAllBy(Predicate<Message> p) {
        return getAll().stream().filter(p).collect(Collectors.toList());
    }

    @Override
    public List<Message> getAll() {
        String sql="SELECT * FROM messages ";
        List<Message> messages = new ArrayList<Message>();
        Message msg=null;
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                LocalDateTime dateTime = LocalDateTime.parse(rs.getString("time"), formatter);

                msg=new Message(rs.getInt("user_id_from")
                        , rs.getInt("user_id_to")
                        , rs.getString("message")
                        , dateTime);
                messages.add(msg);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    @Override
    public boolean add(Message object) {
        boolean result=false;
        String sql="INSERT INTO messages(user_id_from,user_id_to,message,time) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,object.getUser_id_from());
            ps.setInt(2,object.getUser_id_to());
            ps.setString(3,object.getMessage());
            ps.setString(4,object.getTimeString());
            ps.execute();
            result=true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean remove(Message message) {
        String sql="DELETE FROM messages WHERE message_id=?";
        boolean result=false;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,message.getUser_id_to());
            ps.executeUpdate();
            result=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
