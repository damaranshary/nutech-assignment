package com.nutech.nutechassignment.repository.impl;

import com.nutech.nutechassignment.model.UserRole;
import com.nutech.nutechassignment.repository.UserRoleRepository;
import com.nutech.nutechassignment.repository.mapper.UserRoleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleRepositoryImpl implements UserRoleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserRole> findByUser_Id(String email) {
        String sqlQuery = "SELECT * FROM t_user_role WHERE email = ?";

        return jdbcTemplate.query(sqlQuery,
                preparedStatement -> preparedStatement.setString(1, email),
                new UserRoleRowMapper());
    }

    @Override
    public void save(UserRole userRole) {
        String sqlQuery = "INSERT INTO t_user_role " +
                "(email, role_id) VALUES (?, ?)";

        jdbcTemplate.update(sqlQuery,
                preparedStatement -> {
                    preparedStatement.setString(1, userRole.getUserId());
                    preparedStatement.setString(2, userRole.getRoleId());
                });
    }
}
