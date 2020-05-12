package db;

import java.sql.*;

public class DatabaseConnection {
    private final static String URL = "jdbc:postgresql://ec2-79-125-26-232.eu-west-1.compute.amazonaws.com:5432/d4cjshekp7j059";
    private final static String NAME = "mxbergrqugafkk";
    private final static String PWD = "0cf9535daf6724b3aecc2f5a215cb27f099e4e350fc7e5f918a5775801d35707";

    private Connection connection = null;

    private Connection connect() throws SQLException {

        return DriverManager.getConnection(URL, NAME, PWD);

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

    public static void main(String[] args) throws SQLException {
        DatabaseConnection db=new DatabaseConnection();
        Connection con=db.connect();
//        String add="INSERT INTO users(user_name,password) VALUES ('aylin@gmail.com', 'selin@34')";
//        String add2="INSERT INTO users(user_name,password) VALUES ('selin@gmail.com', 'aylin@34')";
//        PreparedStatement stmt2 = con.prepareStatement(add);
//        PreparedStatement stmt3 = con.prepareStatement(add2);
      //  boolean rset = stmt2.execute();
       // boolean rset2 = stmt3.execute();
//        String sql="DELETE FROM users WHERE user_id=?";
//        boolean result=false;
//
//            PreparedStatement ps=con.prepareStatement(sql);
//            ps.setInt(1,1);
//            ps.executeUpdate();

    }
}
