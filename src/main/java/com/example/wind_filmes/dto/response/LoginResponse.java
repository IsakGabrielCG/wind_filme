package com.example.wind_filmes.dto.response;

public record LoginResponse(
        Long userId,
        String name,
        String email,
        String role
) {
}
