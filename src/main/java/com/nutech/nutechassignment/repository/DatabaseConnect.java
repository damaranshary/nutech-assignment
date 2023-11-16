package com.nutech.nutechassignment.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// this will be used only if we didn't use jdbcTemplate
public class DatabaseConnect {
    public static PreparedStatement dbConnect(String sqlQuery) {
        String url = "your-sql-url";
        String username = "your-sql-username";
        String password = "your-sql-password";

        // we will try to connect to the db
        try {
            // Instantiation the mysql Driver class (Class.forName is used for importing/return a class)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // the function will return the prepare statement from the query
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection.prepareStatement(sqlQuery);
        } catch (SQLException | ClassNotFoundException e) {
            // if there is any error in the connection then we will throw exception
            throw new RuntimeException(e);
        }
    }
}
