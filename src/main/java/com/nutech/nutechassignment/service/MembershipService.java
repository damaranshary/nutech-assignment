package com.nutech.nutechassignment.service;

import com.nutech.nutechassignment.model.request.RegisterUserRequest;
import com.nutech.nutechassignment.model.request.UpdateUserRequest;
import com.nutech.nutechassignment.model.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MembershipService {

    Integer saveUser(RegisterUserRequest registerUserRequest);

    UserResponse findUserById(String email);

    UserResponse updateUser(UpdateUserRequest updateUserRequest, String email);

    UserResponse updateUserProfileImage(MultipartFile image, String email) throws IOException;

}
