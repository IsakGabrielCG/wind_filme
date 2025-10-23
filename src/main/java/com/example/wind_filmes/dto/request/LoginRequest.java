package com.example.wind_filmes.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email(message = "email inválido")
        @NotBlank(message = "email é obrigatório")
        String email,

        @NotBlank(message = "senha é obrigatória")
        String password
) {
}
