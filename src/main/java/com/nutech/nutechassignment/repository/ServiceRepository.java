package com.nutech.nutechassignment.repository;

import com.nutech.nutechassignment.model.ServiceLayanan;

import java.util.List;

public interface ServiceRepository {
    List<ServiceLayanan> findAll();

    ServiceLayanan findServiceLayananById(String serviceCode);
}
