package org.tinder.step.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    protected final static String URL = "jdbc:postgresql://ec2-54-246-89-234.eu-west-1.compute.amazonaws.com:5432/d87hm9l72493m5";
    protected final static String NAME = "nqgaomrdgooiuc";
    protected final static String PWD = "6bc08a423fd1532490c7a3f43410f90b43b12a3e26adeb82dc856cd6279ad35e";
//    protected final static String URL = "jdbc:postgresql://localhost:5432/postgres";
//    protected final static String NAME = "postgres";
//    protected final static String PWD = "dreamgirl15";

    private Connection connection ;

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


}
