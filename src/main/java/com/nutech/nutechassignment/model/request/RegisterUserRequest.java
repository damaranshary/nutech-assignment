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
    @Email(message = "Email must be a well-formed email address")
    private String email;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z][A-Za-z ]+[A-Za-z]+$",
            message = "First name should only contains letter and start with capital letter")
    private String first_name;

    @Pattern(regexp = "^[A-Za-z][A-Za-z ]+[A-Za-z]+$",
            message = "Last Name should only contains letter and start with capital letter")
    private String last_name;

    @NotBlank
    @Size(min = 8, message = "Password must at least 8 character long")
    private String password;
}
