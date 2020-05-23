package org.tinder.step.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    protected final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    protected final static String NAME = "postgres";
    protected final static String PWD = "dreamgirl15";

    private Connection connection = null;

    public Connection connect() throws SQLException {

        return DriverManager.getConnection(jdbc_url(), jdbc_username(), jdbc_password());

    }

    public static String jdbc_url() {
        String url = System.getenv("JDBC_DATABASE_URL");
        if (url == null) url=URL;
        return url;
    }

    public static String jdbc_username() {
        String url = System.getenv("JDBC_DATABASE_USERNAME");
        if (url == null) url=NAME;
        return url;
    }

    public static String jdbc_password() {
        String url = System.getenv("JDBC_DATABASE_PASSWORD");
        if (url == null) url=PWD;
        return url;
    }

    public Connection connection() {

        if (connection == null) {
            try {
                connection = connect();
            } catch (SQLException e) {
                throw new IllegalStateException("Something went wrong.");
            }
        }

        return this.connection;

    }

//    public static void main(String[] args) throws SQLException {
//        DatabaseConnection db=new DatabaseConnection();
//        Connection con=db.connect();
////        String add="INSERT INTO users(user_name,password) VALUES ('aylin@gmail.com', 'selin@34')";
////        String add2="INSERT INTO users(user_name,password) VALUES ('selin@gmail.com', 'aylin@34')";
////        PreparedStatement stmt2 = con.prepareStatement(add);
////        PreparedStatement stmt3 = con.prepareStatement(add2);
//      //  boolean rset = stmt2.execute();
//       // boolean rset2 = stmt3.execute();
////        String sql="DELETE FROM users WHERE user_id=?";
////        boolean result=false;
////
////            PreparedStatement ps=con.prepareStatement(sql);
////            ps.setInt(1,2);
////            ps.executeUpdate();
//
////        String sql="SELECT * FROM users WHERE user_id=? ";
////        User user=null;
////
////            PreparedStatement ps=con.prepareStatement(sql);
////            ps.setInt(1,2);
////            ResultSet rs=ps.executeQuery();
////            while (rs.next()) {
////                user = new User(rs.getString("login")
////                        , rs.getString("password")
////                        , rs.getString("name")
////                        , rs.getString("surname")
////                        , rs.getString("job")
////                        , rs.getString("photolink"));
////            }
////
////        System.out.println(user);
//
//
//        String add3="INSERT INTO likes(user_id_from,user_id_to) VALUES (4, 2)";
//        String add4="INSERT INTO likes(user_id_from,user_id_to) VALUES (4, 3)";
//        PreparedStatement stmt4 = con.prepareStatement(add3);
//        PreparedStatement stmt5 = con.prepareStatement(add4);
//          boolean rset3 = stmt4.execute();
//         boolean rset4 = stmt5.execute();
//
//
//    }
}
