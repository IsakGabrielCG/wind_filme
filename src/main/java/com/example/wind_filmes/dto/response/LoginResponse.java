package com.example.wind_filmes.dto.response;

public record LoginResponse(
        String token,
        Long userId,
        String name,
        String email,
        String role
) {
}
