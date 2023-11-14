package com.nutech.nutechassignment.model;


import jakarta.validation.constraints.NotNull;
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
@Table(name = "t_banner")
public class Banner {
    @Id
    @NotNull
    private Long id;

    @Column("banner_name")
    private String bannerName;

    @Column("banner_image")
    private String bannerImage;

    private String description;
}
