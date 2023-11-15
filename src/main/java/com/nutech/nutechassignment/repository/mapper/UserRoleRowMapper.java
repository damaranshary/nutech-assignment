package com.nutech.nutechassignment.repository.mapper;

import com.nutech.nutechassignment.model.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleRowMapper implements RowMapper<UserRole> {

    @Override
    public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserRole userRole = new UserRole();

        userRole.setUserId(rs.getString("email"));
        userRole.setRoleId(rs.getString("role_id"));

        return userRole;
    }
}
