package net.plasmere.dungeons.utils;

import java.sql.*;

public class SQLUtils {
    private static Statement statement;

    public static boolean initialize(String host, int port, String database, String username, String password) {
        Connection connection;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static void execute(String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet query(String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
