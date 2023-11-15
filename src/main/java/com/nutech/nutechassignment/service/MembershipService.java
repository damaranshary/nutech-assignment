package com.nutech.nutechassignment.service;

import com.nutech.nutechassignment.model.request.UpdateUserRequest;
import com.nutech.nutechassignment.model.response.UserResponse;

import java.sql.SQLException;

public interface MembershipService {

    UserResponse findUserById(String email);

    UserResponse updateUser(UpdateUserRequest updateUserRequest, String email);

    UserResponse updateUserProfileImage(String profileImage, String email);

}
