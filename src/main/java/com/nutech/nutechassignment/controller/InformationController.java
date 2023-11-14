package com.nutech.nutechassignment.controller;

import com.nutech.nutechassignment.model.WebResponse;
import com.nutech.nutechassignment.model.response.BannerResponse;
import com.nutech.nutechassignment.model.response.ServiceResponse;
import com.nutech.nutechassignment.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InformationController {

    @Autowired
    private InformationService informationService;

    @GetMapping("/banner")
    public WebResponse<List<BannerResponse>> getBanner() {
        List<BannerResponse> bannerResponseList = informationService.getAllBanner();

        return WebResponse.<List<BannerResponse>>builder().status(200).data(bannerResponseList).message("success").build();
    }

    @GetMapping("/services")
    public WebResponse<List<ServiceResponse>> getServices() {
        List<ServiceResponse> serviceResponseList = informationService.getAllService();

        return WebResponse.<List<ServiceResponse>>builder().status(200).data(serviceResponseList).message("success").build();
    }
}
