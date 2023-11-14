package com.nutech.nutechassignment.repository.impl;

import com.nutech.nutechassignment.model.User;
import com.nutech.nutechassignment.repository.UserRepository;
import com.nutech.nutechassignment.repository.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public User findUserById(String email) throws SQLException {
//        ResultSet resultSet;
//        try (PreparedStatement preparedStatement = dbConnect("SELECT * FROM t_user WHERE email = ?")) {
//            preparedStatement.setString(1, email);
//
//            resultSet = preparedStatement.executeQuery();
//        }
//
//        User user = new User();
//
//        if (resultSet.next()) {
//            user.setEmail(resultSet.getString("email"));
//            user.setFirstName(resultSet.getString("first_name"));
//            user.setLastName(resultSet.getString("last_name"));
//            user.setPassword(resultSet.getString("password"));
//            user.setBalance(resultSet.getLong("balance"));
//
//            return user;
//        } else {
//            throw new SQLException();
//        }
//    }

    public User findUserById(String email) {
        final String sqlQuery = "SELECT * FROM t_user WHERE email = ?";

        // at default jdbcTemplate.query will return a list of data from the table
        List<User> userList = jdbcTemplate.query(sqlQuery,
                preparedStatement -> preparedStatement.setString(1, email),
                new UserRowMapper());

        // Because we only want to get one user, we will only return the first index
        return userList.get(0);
    }

    public Integer updateBalance(String email, Long updatedBalance) {
        String sqlQuery = "UPDATE t_user SET balance = ? WHERE email = ?";

        return jdbcTemplate.update(sqlQuery, updatedBalance, email);
    }
}
