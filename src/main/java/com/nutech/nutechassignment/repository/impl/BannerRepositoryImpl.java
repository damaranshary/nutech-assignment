package com.nutech.nutechassignment.repository.impl;

import com.nutech.nutechassignment.model.Banner;
import com.nutech.nutechassignment.repository.BannerRepository;
import com.nutech.nutechassignment.repository.mapper.BannerRowMapper;
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
public class BannerRepositoryImpl implements BannerRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Banner> findAll() {
        String sqlQuery = "SELECT * FROM t_banner";

        // at default jdbcTemplate.query will return a list of data found with the query from the table
        return jdbcTemplate.query(sqlQuery, new BannerRowMapper());
    }


    // this below is the method without using jdbcTemplate;
//    private List<Banner> findAllWithoutJdbcTemplate() throws SQLException {
//        ResultSet resultSet;
//
//        try (PreparedStatement preparedStatement = dbConnect("SELECT * FROM t_banner")) {
//            resultSet = preparedStatement.executeQuery();
//        }
//
//        List<Banner> bannerList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            Banner banner = new Banner();
//
//            banner.setId(resultSet.getLong("id"));
//            banner.setBannerName(resultSet.getString("banner_name"));
//            banner.setBannerImage(resultSet.getString("banner_image"));
//            banner.setDescription(resultSet.getString("description"));
//
//            bannerList.add(banner);
//        }
//
//        return bannerList;
//    }
}
