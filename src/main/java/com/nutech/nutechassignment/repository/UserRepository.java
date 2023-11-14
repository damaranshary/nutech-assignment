package com.nutech.nutechassignment.repository;

import com.nutech.nutechassignment.model.User;

import java.sql.SQLException;

public interface UserRepository {

    User findUserById(String email);

    Integer updateBalance(String email, Long updatedBalance);

    User updateUser(User user);

    User updateUserImageById(String email, String urlImage);
}
