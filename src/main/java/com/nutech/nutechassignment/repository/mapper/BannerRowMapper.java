package com.nutech.nutechassignment.repository.mapper;

import com.nutech.nutechassignment.model.Banner;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BannerRowMapper implements RowMapper<Banner> {
    @Override
    public Banner mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Banner banner = new Banner();
        banner.setId(resultSet.getLong("id"));
        banner.setBannerName(resultSet.getString("banner_name"));
        banner.setBannerImage(resultSet.getString("banner_image"));
        banner.setDescription(resultSet.getString("description"));

        return banner;
    }
}
