package org.tinder.step.dao;

import org.tinder.step.db.DatabaseConnection;
import org.tinder.step.entity.Activity;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class ActivityDAO implements DAO<Activity> {
    DatabaseConnection db=new DatabaseConnection();
    Connection con;

    public ActivityDAO() {
        this.con = db.connection();
    }

    public boolean add(Activity act){

        boolean result=false;
        int user_id=act.getUser_id();
        ZonedDateTime logout_time = act.getLogout_time();
        String sql2= "INSERT INTO activity(user_id,logout_time) VALUES(?,?) ON CONFLICT ON CONSTRAINT activity_pk DO UPDATE SET logout_time=?";
        try{
            PreparedStatement ps=con.prepareStatement(sql2);
            Timestamp timestamp = new Timestamp(logout_time.toInstant().getEpochSecond() * 1000L);
            ps.setInt(1,user_id);
            ps.setTimestamp(2,timestamp);
            ps.setTimestamp(3,timestamp);
            result=ps.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean remove(Activity object) {
        return false;
    }

    public Activity get(int id){
        Activity act=null;

        String sql="SELECT * FROM activity WHERE user_id=?";
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                act=new Activity(rs.getInt("user_id"),rs.getTimestamp("logout_time").toInstant().atZone(ZoneId.systemDefault()));
            }


        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return act;
    }

    @Override
    public List<Activity> getAllBy(Predicate<Activity> p) {
        return null;
    }

    @Override
    public List<Activity> getAll() {
        return null;
    }

    public static void main(String[] args) {

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("UTC"));
        Activity act=new Activity(1,zonedDateTimeNow);
        Activity act2=new Activity(3,zonedDateTimeNow);
        DAO<Activity> dao=new ActivityDAO();
        dao.add(act);
        dao.add(act2);
        System.out.println(dao.get(1).getLogout_time());
    }

}
