package com.nutech.nutechassignment.controller;

import com.nutech.nutechassignment.model.WebResponse;
import com.nutech.nutechassignment.model.response.BannerResponse;
import com.nutech.nutechassignment.model.response.ServiceResponse;
import com.nutech.nutechassignment.service.InformationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Module Information")
@RestController
public class InformationController {

    @Autowired
    private InformationService informationService;

    @Operation(description = "API Banner Public <br> Used for getting a list of banner (doesn't need an authorization token)")
    @GetMapping(path = "/banner",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<BannerResponse>> getBanner() {
        List<BannerResponse> bannerResponseList = informationService.getAllBanner();

        return WebResponse.<List<BannerResponse>>builder()
                .status(0).data(bannerResponseList)
                .message("Get banners success").build();
    }

    @SecurityRequirement(name = "Token Bearer")
    @Operation(description = "API Services <br> Used for getting a list of services (need an authorization token)")
    @GetMapping(path = "/services",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<ServiceResponse>> getServices() {
        List<ServiceResponse> serviceResponseList = informationService.getAllService();

        return WebResponse.<List<ServiceResponse>>builder()
                .status(0).data(serviceResponseList)
                .message("Get services success").build();
    }
}
