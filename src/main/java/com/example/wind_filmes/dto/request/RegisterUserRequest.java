package com.example.wind_filmes.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank @Size(min = 3, max = 20) String name,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8, max = 20) String password
) {
}
