package org.tinder.step.db;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j2
public class DatabaseConnection {

    protected final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    protected final static String NAME = "postgres";
    protected final static String PWD = "dreamgirl15";

    private static Connection connection;

    public Connection connect() throws SQLException {

        return DriverManager.getConnection(jdbc_url(), jdbc_username(), jdbc_password());

    }

    public static String jdbc_url() {
        String url = System.getenv("JDBC_DATABASE_URL");
        if (url == null) url = URL;
        return url;
    }

    public static String jdbc_username() {
        String url = System.getenv("JDBC_DATABASE_USERNAME");
        if (url == null) url = NAME;
        return url;
    }

    public static String jdbc_password() {
        String url = System.getenv("JDBC_DATABASE_PASSWORD");
        if (url == null) url = PWD;
        return url;
    }

    public Connection connection() {

        if (connection == null) {
            try {
                connection = connect();
            } catch (SQLException e) {
                log.error(new IllegalStateException("Something went wrong."));

            }
        }

        return this.connection;

    }


}
