package com.nutech.nutechassignment.service.impl;

import com.cloudinary.Cloudinary;
import com.nutech.nutechassignment.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadImageToCloudinaryService(MultipartFile image) throws IOException {
        Map data = this.cloudinary.uploader().upload(image.getBytes(), Map.of());

        return (String) data.get("url");
    }
}
