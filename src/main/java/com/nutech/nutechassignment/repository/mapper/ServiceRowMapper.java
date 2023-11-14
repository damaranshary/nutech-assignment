package com.nutech.nutechassignment.repository.mapper;

import com.nutech.nutechassignment.model.ServiceLayanan;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceRowMapper implements RowMapper<ServiceLayanan> {
    @Override
    public ServiceLayanan mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
