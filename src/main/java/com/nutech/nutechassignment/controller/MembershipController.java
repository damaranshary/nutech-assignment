package com.nutech.nutechassignment.controller;

import com.nutech.nutechassignment.exception.ImageFormatException;
import com.nutech.nutechassignment.exception.UserAlreadyExistsException;
import com.nutech.nutechassignment.model.WebResponse;
import com.nutech.nutechassignment.model.request.RegisterUserRequest;
import com.nutech.nutechassignment.model.request.UpdateUserImageRequest;
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
import java.util.Objects;

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
        UserResponse user = membershipService.findUserById(registerUserRequest.getEmail());

        // we will try to find if there is a user that registered with the same email as the request
        if (user != null) {
            throw new UserAlreadyExistsException();
        }

        int result = membershipService.saveUser(registerUserRequest);

        if (result <= 0) {
            return WebResponse.<String>builder().status(102).data(null).message(String.valueOf(HttpStatus.BAD_REQUEST)).build();
        }

        return WebResponse.<String>builder()
                .status(0).data(null)
                .message("Registration success, you can login with your credentials").build();
    }

    @GetMapping(
            path = "/profile",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> getProfile(Authentication authentication) {
        String userEmail = authentication.getName();
        UserResponse userResponse = membershipService.findUserById(userEmail);

        return WebResponse.<UserResponse>builder()
                .status(0).data(userResponse)
                .message("Get profile success").build();
    }

    @PutMapping(
            path = "/profile/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> updateProfile(@RequestBody UpdateUserRequest updateUserRequest,
                                                   Authentication authentication) {
        String userEmail = authentication.getName();
        UserResponse userResponse = membershipService.updateUser(updateUserRequest, userEmail);

        return WebResponse.<UserResponse>builder()
                .status(0).data(userResponse)
                .message("Profile data successfully updated").build();
    }

    @PutMapping(value = "/profile/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> updateProfileImage(@RequestPart MultipartFile image,
                                                        Authentication authentication) throws IOException {
        String userEmail = authentication.getName();

        if (!Objects.equals(image.getContentType(), "image/png") && !Objects.equals(image.getContentType(), "image/jpeg")) {
            throw new ImageFormatException();
        }

        UpdateUserImageRequest userImageRequest = new UpdateUserImageRequest();
        userImageRequest.setImage(image);

        UserResponse userResponse = membershipService.updateUserProfileImage(userImageRequest, userEmail);

        return WebResponse.<UserResponse>builder()
                .status(0).data(userResponse)
                .message("Profile image successfully updated").build();
    }
}
