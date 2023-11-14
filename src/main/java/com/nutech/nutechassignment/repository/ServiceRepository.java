package com.nutech.nutechassignment.repository;

import com.nutech.nutechassignment.model.ServiceLayanan;

import java.sql.SQLException;
import java.util.List;

public interface ServiceRepository {
    List<ServiceLayanan> findAll();

    ServiceLayanan findServiceLayananByService_Code(String service_code);
}
