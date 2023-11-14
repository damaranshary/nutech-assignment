package com.nutech.nutechassignment.service;

import com.nutech.nutechassignment.model.response.UserResponse;

import java.sql.SQLException;

public interface MembershipService {

    UserResponse findUserById(String email);

}
