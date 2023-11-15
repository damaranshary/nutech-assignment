package com.nutech.nutechassignment.controller;

import com.nutech.nutechassignment.dto.UserDto;
import com.nutech.nutechassignment.model.WebResponse;
import com.nutech.nutechassignment.model.request.UpdateUserImageRequest;
import com.nutech.nutechassignment.model.request.UpdateUserRequest;
import com.nutech.nutechassignment.model.response.UserResponse;
import com.nutech.nutechassignment.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;

@RestController
@RequestMapping("/profile")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> getProfile()  {
        UserResponse user = membershipService.findUserById("damar@email.com");

        return WebResponse.<UserResponse>builder()
                .status(200)
                .data(user)
                .message("success")
                .build();
    }

    @PutMapping(value = "/update")
    public WebResponse<UserResponse> updateProfile(@RequestBody UpdateUserRequest userRequest) {
        UserResponse user = membershipService.updateUser(userRequest, "damar@email.com");

        return WebResponse.<UserResponse>builder()
                .status(200)
                .data(user)
                .message("success")
                .build();
    }

    @PutMapping(value = "/image",
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> updateProfileImage(@RequestBody UpdateUserImageRequest updateUserImageRequest) {
        UserResponse user = membershipService.updateUserProfileImage(updateUserImageRequest.getProfile_image(), "damar@email.com");

        return WebResponse.<UserResponse>builder()
                .status(200)
                .data(user)
                .message("success")
                .build();
    }
}
