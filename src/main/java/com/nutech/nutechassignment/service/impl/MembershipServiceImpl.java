package com.nutech.nutechassignment.service.impl;

import com.nutech.nutechassignment.model.User;
import com.nutech.nutechassignment.model.request.UpdateUserImageRequest;
import com.nutech.nutechassignment.model.request.UpdateUserRequest;
import com.nutech.nutechassignment.model.response.UserResponse;
import com.nutech.nutechassignment.repository.UserRepository;
import com.nutech.nutechassignment.service.CloudinaryService;
import com.nutech.nutechassignment.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public UserResponse findUserById(String email) {
        User user = userRepository.findUserById(email);

        return convertUserToUserResponse(user);
    }

    @Override
    public UserResponse updateUser(UpdateUserRequest updateUserRequest, String email) {
        User user = userRepository.findUserById(email);

        user.setFirstName(updateUserRequest.getFirst_name());
        user.setLastName(updateUserRequest.getLast_name());

        return convertUserToUserResponse(userRepository.updateUser(user));
    }

    @Override
    public UserResponse updateUserProfileImage(MultipartFile image, String email) throws IOException {
        String cloudinaryUrlImage = cloudinaryService.uploadImageToCloudinaryService(image);

        User user = userRepository.updateUserProfileImageById(cloudinaryUrlImage, email);

        return convertUserToUserResponse(user);
    }

    private UserResponse convertUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();

        userResponse.setEmail(user.getEmail());
        userResponse.setFirst_name(user.getFirstName());
        userResponse.setLast_name(user.getLastName());
        userResponse.setProfile_image(user.getProfileImage());

        return userResponse;
    }
}
