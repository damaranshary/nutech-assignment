package com.nutech.nutechassignment.repository.mapper;

import com.nutech.nutechassignment.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();

        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setPassword(resultSet.getString("password"));
        user.setBalance(resultSet.getLong("balance"));
        user.setProfileImage(resultSet.getString("profile_image"));

        return user;
    }
}
