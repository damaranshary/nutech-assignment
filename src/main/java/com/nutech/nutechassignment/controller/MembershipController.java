package com.nutech.nutechassignment.controller;

import com.nutech.nutechassignment.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profile")
public class MembershipController {

    @GetMapping
    public void getProfile() {

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
