package com.example.wind_filmes.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @NotBlank(message = "nome é obrigatorio")
        @Size(min = 3, max = 20, message = "nome deve ter entre 3 e 20 caracteres")
        String name
) {
}
