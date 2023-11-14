package com.nutech.nutechassignment.service;

import com.nutech.nutechassignment.model.Banner;
import com.nutech.nutechassignment.model.ServiceLayanan;
import com.nutech.nutechassignment.model.response.BannerResponse;
import com.nutech.nutechassignment.model.response.ServiceResponse;
import com.nutech.nutechassignment.repository.BannerRepository;
import com.nutech.nutechassignment.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InformationServiceImpl implements InformationService{
    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<BannerResponse> getAllBanner() {
        return bannerRepository.findAll()
                .stream()
                .map(this::convertBannerToBannerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceResponse> getAllService() {
        return serviceRepository.findAll()
                .stream()
                .map(this::convertServiceToServiceResponse)
                .collect(Collectors.toList());
    }

    private BannerResponse convertBannerToBannerResponse(Banner banner) {
        BannerResponse bannerResponse = new BannerResponse();
        bannerResponse.setBanner_name(banner.getBannerName());
        bannerResponse.setBanner_image(banner.getBannerImage());
        bannerResponse.setDescription(banner.getDescription());

        return bannerResponse;
    }

    private ServiceResponse convertServiceToServiceResponse(ServiceLayanan serviceLayanan) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setService_code(serviceLayanan.getServiceCode());
        serviceResponse.setService_name(serviceLayanan.getServiceName());
        serviceResponse.setService_icon(serviceLayanan.getServiceIcon());
        serviceResponse.setService_tariff(serviceLayanan.getServiceTariff());

        return serviceResponse;
    }
}
