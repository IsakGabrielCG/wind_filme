package com.example.wind_filmes.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {

    @NotBlank(message = "O nome da categoria é obrigatório")
    private String name;
}
