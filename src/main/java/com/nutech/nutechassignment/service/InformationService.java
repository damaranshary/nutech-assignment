package com.nutech.nutechassignment.service;

import com.nutech.nutechassignment.model.response.BannerResponse;
import com.nutech.nutechassignment.model.response.ServiceResponse;

import java.util.List;

public interface InformationService {
    List<BannerResponse> getAllBanner();

    List<ServiceResponse> getAllService();
}
