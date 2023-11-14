package com.nutech.nutechassignment.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionServiceResponse {
    private String invoice_number;
    private String service_code;
    private String service_name;
    private String transaction_type;
    private Long total_amount;
    private LocalDateTime created_on;
}
