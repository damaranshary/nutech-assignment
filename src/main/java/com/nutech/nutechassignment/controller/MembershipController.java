package com.nutech.nutechassignment.controller;

import com.nutech.nutechassignment.model.WebResponse;
import com.nutech.nutechassignment.model.request.RegisterUserRequest;
import com.nutech.nutechassignment.model.request.UpdateUserRequest;
import com.nutech.nutechassignment.model.response.UserResponse;
import com.nutech.nutechassignment.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping()
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @PostMapping(
            path = "/registration",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> doRegistration(@RequestBody RegisterUserRequest registerUserRequest) {
        int result = membershipService.saveUser(registerUserRequest);

        if (result > 0) {
            return WebResponse.<String>builder().status(200)
                    .data(null)
                    .message("Registration success, you can login with your credentials").build();
        } else {
            return WebResponse.<String>builder().status(102).data(null).message(String.valueOf(HttpStatus.BAD_REQUEST)).build();
        }
    }

    @GetMapping(
            path = "/profile",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> getProfile(Authentication authentication) {
        UserResponse user = membershipService.findUserById(authentication.getName());

        return WebResponse.<UserResponse>builder()
                .status(200)
                .data(user)
                .message("success")
                .build();
    }

    @PutMapping(
            path = "/profile/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> updateProfile(@RequestBody UpdateUserRequest userRequest,
                                                   Authentication authentication) {
        UserResponse user = membershipService.updateUser(userRequest, authentication.getName());

        return WebResponse.<UserResponse>builder()
                .status(200)
                .data(user)
                .message("success")
                .build();
    }

    @PutMapping(value = "/profile/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> updateProfileImage(@RequestPart MultipartFile image,
                                                        Authentication authentication) throws IOException {
        UserResponse user = membershipService.updateUserProfileImage(image, authentication.getName());

        return WebResponse.<UserResponse>builder()
                .status(200)
                .data(user)
                .message("success")
                .build();
    }
}
