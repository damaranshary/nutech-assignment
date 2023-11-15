package com.nutech.nutechassignment.service.impl;

import com.nutech.nutechassignment.model.User;
import com.nutech.nutechassignment.model.request.RegisterUserRequest;
import com.nutech.nutechassignment.model.request.UpdateUserRequest;
import com.nutech.nutechassignment.model.response.UserResponse;
import com.nutech.nutechassignment.repository.UserRepository;
import com.nutech.nutechassignment.service.CloudinaryService;
import com.nutech.nutechassignment.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Integer saveUser(RegisterUserRequest registerUserRequest) {
        User user = new User();

        user.setEmail(registerUserRequest.getEmail());
        user.setFirstName(registerUserRequest.getFirst_name());
        user.setLastName(registerUserRequest.getLast_name());
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        user.setProfileImage(null);
        user.setBalance(0L);

        return userRepository.save(user);
    }

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
