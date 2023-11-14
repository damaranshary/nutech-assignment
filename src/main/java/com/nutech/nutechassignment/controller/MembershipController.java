package com.nutech.nutechassignment.controller;

import com.nutech.nutechassignment.dto.UserDto;
import com.nutech.nutechassignment.model.WebResponse;
import com.nutech.nutechassignment.model.response.UserResponse;
import com.nutech.nutechassignment.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;

@RestController
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @GetMapping(path ="/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> getProfile()  {
        System.out.println("DO SOMETHING");
        UserResponse user = membershipService.findUserById("damar@email.com");

        System.out.println(user);
        return WebResponse.<UserResponse>builder()
                .status(200)
                .data(user)
                .message("hahahaha")
                .build();
    }

    @PutMapping(value = "/update")
    public void updateProfile(@RequestBody UserDto userDto) {

    }

    @PutMapping(value = "/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateProfileImage(@RequestBody MultipartFile image) {

    }
}
