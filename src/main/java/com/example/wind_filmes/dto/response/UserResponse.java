package com.example.wind_filmes.dto.response;

public record UserResponse(
        Long id,
        String name,
        String email,
        String role
) {
}
