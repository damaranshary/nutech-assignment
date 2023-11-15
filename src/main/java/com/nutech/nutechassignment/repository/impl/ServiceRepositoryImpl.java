package com.nutech.nutechassignment.repository.impl;

import com.nutech.nutechassignment.exception.ServiceNotFoundException;
import com.nutech.nutechassignment.model.ServiceLayanan;
import com.nutech.nutechassignment.repository.ServiceRepository;
import com.nutech.nutechassignment.repository.mapper.ServiceRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.nutech.nutechassignment.repository.DatabaseConnect.dbConnect;

@Repository
public class ServiceRepositoryImpl implements ServiceRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ServiceLayanan> findAll() {
        String sqlQuery = "SELECT * FROM t_service";

        // at default jdbcTemplate.query will return a list of data found with the query from the table
        return jdbcTemplate.query(sqlQuery, new ServiceRowMapper());
    }

    public ServiceLayanan findServiceLayananById(String serviceCode) {
        String sqlQuery = "SELECT * FROM t_service WHERE code = ?";

        List<ServiceLayanan> serviceLayananList = jdbcTemplate.query(sqlQuery,
                preparedStatement -> preparedStatement.setString(1, serviceCode),
                new ServiceRowMapper());

        if (serviceLayananList.size() < 1) {
            return null;
        }

        return serviceLayananList.get(0);
    }


    // this below is the method without using jdbcTemplate;
//    private List<ServiceLayanan> findAllWithoutJdbcTemplate() throws SQLException {
//        ResultSet resultSet;
//
//        try (PreparedStatement preparedStatement = dbConnect("SELECT * FROM t_service")) {
//            resultSet = preparedStatement.executeQuery();
//        }
//
//        List<ServiceLayanan> serviceList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            ServiceLayanan service = new ServiceLayanan();
//
//            service.setServiceCode(resultSet.getString("code"));
//            service.setServiceName(resultSet.getString("name"));
//            service.setServiceIcon(resultSet.getString("icon"));
//            service.setServiceTariff(resultSet.getLong("tariff"));
//
//            serviceList.add(service);
//        }
//
//        return serviceList;
//    }

//    public ServiceLayanan findServiceLayananByService_CodeWithoutJdbcTemplate(String service_code) throws SQLException {
//        ResultSet resultSet;
//        ServiceLayanan serviceLayanan = new ServiceLayanan();
//        try (PreparedStatement preparedStatement = dbConnect("SELECT * FROM t_service WHERE code = ?")) {
//            preparedStatement.setString(1, service_code);
//
//            resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                serviceLayanan.setServiceCode(resultSet.getString("code"));
//                serviceLayanan.setServiceName(resultSet.getString("name"));
//                serviceLayanan.setServiceIcon(resultSet.getString("icon"));
//                serviceLayanan.setServiceTariff(resultSet.getLong("tariff"));
//            }
//        }
//
//        return serviceLayanan;
//    }
}
