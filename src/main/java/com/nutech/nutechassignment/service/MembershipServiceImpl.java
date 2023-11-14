package com.nutech.nutechassignment.service;

import com.nutech.nutechassignment.model.User;
import com.nutech.nutechassignment.model.response.UserResponse;
import com.nutech.nutechassignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse findUserById(String email) {
        User user = userRepository.findUserById(email);

        return convertUserToUserResponse(user);
    }

    private UserResponse convertUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();

        userResponse.setEmail(user.getEmail());
        userResponse.setFirst_name(user.getFirstName());
        userResponse.setLast_name(user.getLastName());
        userResponse.setProfile_image(null);

        return userResponse;
    }
}
