package org.tinder.step.dao;

import lombok.extern.log4j.Log4j2;
import org.tinder.step.db.DatabaseConnection;
import org.tinder.step.entity.Message;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class MessageDAO implements DAO<Message> {
    DatabaseConnection db = new DatabaseConnection();
    Connection con;

    public MessageDAO() {
        this.con = db.connection();
    }

    @Override
    public Optional<Message> get(int id) {
        String sql = "SELECT * FROM messages WHERE message_id=? ";
        Message msg = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();


            msg = new Message(rs.getInt("user_id_from")
                    , rs.getInt("user_id_to")
                    , rs.getString("message")
                    , rs.getTimestamp("time").toInstant().atZone(ZoneId.systemDefault()));
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return Optional.ofNullable(msg);
    }

    public List<Message> getMessagesForChat(int user_id_from, int user_id_to) {
        String sql = "SELECT * FROM messages WHERE user_id_from=? AND user_id_to=? OR  user_id_from=? AND user_id_to=? ";
        List<Message> messages = new ArrayList<Message>();
        Message msg = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id_from);
            ps.setInt(2, user_id_to);
            ps.setInt(3, user_id_to);
            ps.setInt(4, user_id_from);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                msg = new Message(rs.getInt("user_id_from")
                        , rs.getInt("user_id_to")
                        , rs.getString("message")
                        , rs.getTimestamp("time").toInstant().atZone(ZoneId.systemDefault()));
                messages.add(msg);
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return messages;
    }


    @Override
    public List<Message> getAll() {
        String sql = "SELECT * FROM messages ";
        List<Message> messages = new ArrayList<Message>();
        Message msg = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                msg = new Message(rs.getInt("user_id_from")
                        , rs.getInt("user_id_to")
                        , rs.getString("message")
                        , rs.getTimestamp("time").toInstant().atZone(ZoneId.systemDefault()));
                messages.add(msg);
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return messages;
    }

    @Override
    public boolean add(Message object) {
        boolean result = false;
        String sql = "INSERT INTO messages(user_id_from,user_id_to,message,time) VALUES(?,?,?,?)";
        try {
            Timestamp timestamp = new Timestamp(object.getTime().toInstant().getEpochSecond() * 1000L);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, object.getUser_id_from());
            ps.setInt(2, object.getUser_id_to());
            ps.setString(3, object.getMessage());
            ps.setTimestamp(4, timestamp);
            ps.execute();
            result = true;

        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean remove(Message message) {
        String sql = "DELETE FROM messages WHERE message_id=?";
        boolean result = false;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, message.getUser_id_to());
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;
    }
}
