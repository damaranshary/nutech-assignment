package com.nutech.nutechassignment.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_service")
public class Service {
    @Id
    @Column("code")
    private String serviceCode;

    @Column("name")
    private String serviceName;

    @Column("icon")
    private String serviceIcon;

    @Column("tariff")
    private Long serviceTariff;
}
