package com.nutech.nutechassignment.model;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_user_role")
public class UserRole {
    @Column("email")
    private String userId;

    @Column("role_id")
    private String roleId;
}
