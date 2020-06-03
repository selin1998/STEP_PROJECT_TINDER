package org.tinder.step.dao;

import lombok.extern.log4j.Log4j2;
import org.tinder.step.db.DatabaseConnection;
import org.tinder.step.entity.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2

public class LikesDAO implements DAO<Like> {
    DatabaseConnection db=new DatabaseConnection();
    Connection con;


    public LikesDAO() throws SQLException {
        this.con = db.connection();
    }

    @Override
    public Optional<Like> get(int id) {
        String sql="SELECT * FROM likes WHERE like_id=? ";
        Like like=null;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();

            like=new Like(rs.getInt("user_id_from")
                    , rs.getInt("user_id_to"));
        } catch (SQLException e) {
            log.error(e.getMessage());
//            e.printStackTrace();
        }
        return Optional.ofNullable(like);
    }


    @Override
    public List<Like> getAll() {
        String sql="SELECT * FROM likes ";
        List<Like> likes = new ArrayList<>();
        Like like=null;
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                like=new Like(rs.getInt("user_id_from")
                        , rs.getInt("user_id_to"));
                likes.add(like);
            }

        } catch (SQLException e) {
            log.error(e.getMessage());

//            e.printStackTrace();
        }

        return likes;
    }

    @Override
    public boolean add(Like object) {
        boolean result=false;
        String sql= "INSERT INTO likes(user_id_from,user_id_to) VALUES(?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,object.getUser_id_from());
            ps.setInt(2,object.getUser_id_to());
            ps.execute();
            result=true;

        } catch (SQLException e) {
            log.error(e.getMessage());

//            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean remove(Like like) {
        String sql="DELETE FROM likes WHERE user_id_from=? AND user_id_to=?";
        boolean result=false;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,like.getUser_id_from());
            ps.setInt(2,like.getUser_id_to());
            ps.executeUpdate();
            result=true;
        } catch (SQLException e) {
            log.error(e.getMessage());

//            e.printStackTrace();
        }
        return result;
    }

}
