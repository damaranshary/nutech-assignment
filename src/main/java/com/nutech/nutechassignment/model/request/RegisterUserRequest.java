package com.nutech.nutechassignment.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z][A-Za-z ]+[A-Za-z]+$")
    private String first_name;

    @Pattern(regexp = "^[A-Za-z][A-Za-z ]+[A-Za-z]+$")
    private String last_name;

    @NotBlank
    @Size(min = 8)
    private String password;
}
