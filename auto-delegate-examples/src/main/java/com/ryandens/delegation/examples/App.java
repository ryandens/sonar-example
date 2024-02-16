package com.ryandens.delegation.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public final class App {

    public static void main(final String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")) {
            Logger.getLogger(App.class.getName()).info("" + new App().doStuff(connection));
        }
    }

    private int doStuff(final Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("CALL IDENTITY();");
        if (!resultSet.next()) {
            throw new SQLException("couldn't find any identity record");
        }
        int id = resultSet.getInt(1);
        resultSet.close();
        return id;
    }
}
