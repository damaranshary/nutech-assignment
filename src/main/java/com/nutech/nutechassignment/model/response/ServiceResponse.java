package com.nutech.nutechassignment.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceResponse {
    private String service_code;
    private String service_name;
    private String service_icon;
    private Long service_tariff;
}
