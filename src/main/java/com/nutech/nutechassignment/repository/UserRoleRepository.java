package com.nutech.nutechassignment.repository;

import com.nutech.nutechassignment.model.UserRole;

import java.util.List;

public interface UserRoleRepository {

    List<UserRole> findByUser_Id(String email);

    void save(UserRole userRole);
}
