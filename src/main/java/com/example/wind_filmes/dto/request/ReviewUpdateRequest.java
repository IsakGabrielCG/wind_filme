package com.example.wind_filmes.dto.request;

import lombok.Data;

@Data
public class ReviewUpdateRequest {
    private double rating;
    private String comment;
}
