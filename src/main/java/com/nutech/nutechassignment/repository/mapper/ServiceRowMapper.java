package com.nutech.nutechassignment.repository.mapper;

import com.nutech.nutechassignment.model.ServiceLayanan;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceRowMapper implements RowMapper<ServiceLayanan> {
    @Override
    public ServiceLayanan mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ServiceLayanan service = new ServiceLayanan();

        service.setServiceCode(resultSet.getString("code"));
        service.setServiceName(resultSet.getString("name"));
        service.setServiceIcon(resultSet.getString("icon"));
        service.setServiceTariff(resultSet.getLong("tariff"));

        return service;
    }
}
