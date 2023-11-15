package com.nutech.nutechassignment.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {
    @NotBlank
    @Pattern(regexp = "^[A-Za-z][A-Za-z ]+[A-Za-z]+$", message = "Name can only contains letter and whitespace")
    private String first_name;

    @Pattern(regexp = "^[A-Za-z][A-Za-z ]+[A-Za-z]+$", message = "Name can only contains letter and whitespace")
    private String last_name;
}
