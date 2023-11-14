package com.nutech.nutechassignment.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnect {
    public static PreparedStatement dbConnect(String sqlQuery) {
        String url = "jdbc:mysql://localhost:3306/sims_ppob";
        String username = "root";
        String password = "";

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
