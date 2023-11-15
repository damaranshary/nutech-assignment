package com.nutech.nutechassignment.repository.impl;

import com.nutech.nutechassignment.model.User;
import com.nutech.nutechassignment.repository.UserRepository;
import com.nutech.nutechassignment.repository.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer save(User user) {
        String sqlQuery = "INSERT INTO t_user " +
                "(email, first_name, last_name, password, profile_image, balance)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sqlQuery,
                preparedStatement -> {
                    preparedStatement.setString(1, user.getEmail());
                    preparedStatement.setString(2, user.getFirstName());
                    preparedStatement.setString(3, user.getLastName());
                    preparedStatement.setString(4, user.getPassword());
                    preparedStatement.setString(5, user.getProfileImage());
                    preparedStatement.setLong(6, user.getBalance());
                });
    }

    @Override
    public User findUserById(String email) {
        String sqlQuery = "SELECT * FROM t_user WHERE email = ?";

        // at default jdbcTemplate.query will return a list of data from the table
        List<User> userList = jdbcTemplate.query(sqlQuery,
                preparedStatement -> preparedStatement.setString(1, email),
                new UserRowMapper());

        // Because we only want to get one user, we will only return the first index
        return userList.get(0);
    }

    @Override
    public Integer updateBalance(String email, Long updatedBalance) {
        String sqlQuery = "UPDATE t_user SET balance = ? WHERE email = ?";

        return jdbcTemplate.update(sqlQuery, preparedStatement -> {
            preparedStatement.setLong(1, updatedBalance);
            preparedStatement.setString(2, email);
        });
    }

    @Override
    public User updateUser(User user) {
        String sqlQuery = "UPDATE t_user SET first_name = ?, last_name = ? WHERE email = ?";

        int result = jdbcTemplate.update(sqlQuery,
                preparedStatement -> {
                    preparedStatement.setString(1, user.getFirstName());
                    preparedStatement.setString(2, user.getLastName());
                    preparedStatement.setString(3, user.getEmail());
                });

        if (result <= 0) {
            return null;
        }

        return findUserById(user.getEmail());
    }

    @Override
    public User updateUserProfileImageById(String profileImage, String email) {
        String sqlQuery = "UPDATE t_user SET profile_image = ? WHERE email = ?";

        int result = jdbcTemplate.update(sqlQuery,
                preparedStatement -> {
                    preparedStatement.setString(1, profileImage);
                    preparedStatement.setString(2, email);
                });

        if (result <= 0) {
            return null;
        }

        return findUserById(email);
    }

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
}
