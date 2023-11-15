package com.nutech.nutechassignment.service.impl;

import com.nutech.nutechassignment.exception.UserNotFoundException;
import com.nutech.nutechassignment.model.User;
import com.nutech.nutechassignment.model.UserRole;
import com.nutech.nutechassignment.model.request.RegisterUserRequest;
import com.nutech.nutechassignment.model.request.UpdateUserImageRequest;
import com.nutech.nutechassignment.model.request.UpdateUserRequest;
import com.nutech.nutechassignment.model.response.UserResponse;
import com.nutech.nutechassignment.repository.UserRepository;
import com.nutech.nutechassignment.repository.UserRoleRepository;
import com.nutech.nutechassignment.service.CloudinaryService;
import com.nutech.nutechassignment.service.MembershipService;
import com.nutech.nutechassignment.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Integer saveUser(RegisterUserRequest request) {
        validationService.validate(request);
        User user = new User();

        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirst_name());
        user.setLastName(request.getLast_name());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // at the start of the creation of the account we create the default avatar from my cloudinary
        user.setProfileImage("https://res.cloudinary.com/dyfdsuryj/image/upload/v1700052646/default_avatar.png");
        // and also we set the balance as 0
        user.setBalance(0L);

        int result = userRepository.save(user);

        // we assign the user role after the user already registered in the database
        if (result > 0 ){
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getEmail());
            userRole.setRoleId("ROLE_USER");

            userRoleRepository.save(userRole);
        }

        return result;
    }

    @Override
    public UserResponse findUserById(String email) {
        User user = userRepository.findUserById(email);

        if (user == null) {
            return null;
        }

        return convertUserToUserResponse(user);
    }

    @Override
    public UserResponse updateUser(UpdateUserRequest request, String email) {
        validationService.validate(request);
        User user = userRepository.findUserById(email);

        if (user == null) {
            throw new UserNotFoundException();
        }

        user.setFirstName(request.getFirst_name());
        user.setLastName(request.getLast_name());

        return convertUserToUserResponse(userRepository.updateUser(user));
    }

    @Override
    public UserResponse updateUserProfileImage(UpdateUserImageRequest request, String email) throws IOException {
        validationService.validate(request);
        // we will upload the requested image to cloudinary
        String cloudinaryUrlImage = cloudinaryService.uploadImageToCloudinaryService(request.getImage());

        // then we will assign the url we get from the cloudinary response to the url_image in the database
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
