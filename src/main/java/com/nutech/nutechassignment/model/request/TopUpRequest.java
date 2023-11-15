package com.nutech.nutechassignment.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopUpRequest {
    @NotNull
    @Positive(message = "Top up amount must be greater than 0")
    private Long top_up_amount;
}
