package dao;

import entity.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikesDAO implements DAO<Like> {

    private Connection con;


    public LikesDAO(Connection con) {
        this.con = con;
    }

    @Override
    public Like get(int id) {
        String sql="SELECT * FROM likes WHERE like_id=? ";
        Like like=null;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();

            like=new Like(rs.getInt("user_id_from")
                    , rs.getInt("user_id_to"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return like;
    }

    @Override
    public int getMaxId() {
        return 0;
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
            e.printStackTrace();
        }

        return likes;
    }

    @Override
    public boolean add(Like object) {
        boolean result=false;
        String sql="INSERT INTO likes(user_id_from,user_id_to) VALUES(?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,object.getUser_id_from());
            ps.setInt(2,object.getUser_id_to());
            ps.execute();
            result=true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean remove(int id) {
        String sql="DELETE FROM likes WHERE like_id=?";
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
