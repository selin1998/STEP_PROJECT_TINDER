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
            ResultSet rs=ps.executeQuery(sql);

            like=new Like(rs.getString("login_from")
                    , rs.getString("login_to"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return like;
    }

    @Override
    public List<Like> getAll() {
        String sql="SELECT * FROM likes ";
        List<Like> messages = new ArrayList<>();
        Like like=null;
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery(sql);
            while(rs.next()){
                like=new Like(rs.getString("login_from")
                        , rs.getString("login_to"));
                messages.add(like);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    @Override
    public boolean add(Like object) {
        boolean result=false;
        String sql="INSERT INTO likes(login_from,login_to) VALUES(?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,object.getLogin_from());
            ps.setString(2,object.getLogin_to());
            ps.executeUpdate();
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
