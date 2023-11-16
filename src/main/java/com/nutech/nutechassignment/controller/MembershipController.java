package com.nutech.nutechassignment.controller;

import com.nutech.nutechassignment.exception.ImageFormatException;
import com.nutech.nutechassignment.exception.UserAlreadyExistsException;
import com.nutech.nutechassignment.model.WebResponse;
import com.nutech.nutechassignment.model.request.RegisterUserRequest;
import com.nutech.nutechassignment.model.request.UpdateUserImageRequest;
import com.nutech.nutechassignment.model.request.UpdateUserRequest;
import com.nutech.nutechassignment.model.response.UserResponse;
import com.nutech.nutechassignment.service.MembershipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Tag(name = "Module Membership")
@RestController
@RequestMapping()
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @Operation(description = "Profile user API <br> Used to register new user (doesn't need an authorization token)")
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

    @SecurityRequirement(name = "Token Bearer")
    @Operation(description = "Profile user API <br> Used for getting a detailed info from logged in user (need an authorization token)")
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

    @SecurityRequirement(name = "Token Bearer")
    @Operation(description = "Update profile user API <br> Used to update users info (name) (need an authorization token)")
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

    @SecurityRequirement(name = "Token Bearer")
    @Operation(description = "Update profile image user API <br> Used to update user profile image (need an authorization token)")
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
