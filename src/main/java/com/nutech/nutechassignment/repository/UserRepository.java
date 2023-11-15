package com.nutech.nutechassignment.repository;

import com.nutech.nutechassignment.model.User;

public interface UserRepository {

    Integer save(User user);

    User findUserById(String email);

    Integer updateBalance(String email, Long updatedBalance);

    User updateUser(User user);

    User updateUserProfileImageById( String profileImage, String email);
}
