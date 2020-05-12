package dao;

import entity.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO implements DAO<Message> {
    private Connection con;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public MessageDAO(Connection con) {
        this.con = con;
    }

    @Override
    public Message get(int id) {
        String sql="SELECT * FROM messages WHERE message_id=? ";
        Message msg=null;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery(sql);

            LocalDateTime dateTime = LocalDateTime.parse(rs.getString("time"), formatter);

            msg=new Message(rs.getString("login_from")
                    , rs.getString("login_to")
                    , rs.getString("message")
                    , dateTime);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public List<Message> getAll() {
        String sql="SELECT * FROM messages ";
        List<Message> messages = new ArrayList<Message>();
        Message msg=null;
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery(sql);
            while(rs.next()){
                LocalDateTime dateTime = LocalDateTime.parse(rs.getString("time"), formatter);

                msg=new Message(rs.getString("login_from")
                        , rs.getString("login_to")
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
        String sql="INSERT INTO messages (login_from,login_to,message,time) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,object.getUserLoginFrom());
            ps.setString(2,object.getUserLoginTo());
            ps.setString(3,object.getMessage());
            ps.setString(4,object.getTime());
            ps.executeUpdate();
            result=true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean remove(int id) {
        String sql="DELETE FROM messages WHERE message_id=?";
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
