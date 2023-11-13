package com.nutech.nutechassignment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InformationController {

    @GetMapping("/banner")
    public void getBanner() {

    }

    @GetMapping("/services")
    public void getServices() {

    }
}
