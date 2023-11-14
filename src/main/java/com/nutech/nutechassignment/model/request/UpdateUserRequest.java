package com.nutech.nutechassignment.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z][A-Za-z ]+[A-Za-z]+$")
    private String first_name;

    @Pattern(regexp = "^[A-Za-z][A-Za-z ]+[A-Za-z]+$")
    private String last_name;
}
