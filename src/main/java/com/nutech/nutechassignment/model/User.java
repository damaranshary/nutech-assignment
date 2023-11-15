package com.nutech.nutechassignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user")
public class User {
    @Id
    private String email;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    private String password;

    @Column("profile_image")
    private String profileImage;

    private Long balance;
}
