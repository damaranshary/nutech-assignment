package com.nutech.nutechassignment.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    String uploadImageToCloudinaryService(MultipartFile image) throws IOException;
}
