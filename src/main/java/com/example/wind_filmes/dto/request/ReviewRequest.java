package com.example.wind_filmes.dto.request;

import lombok.Data;

@Data
public class ReviewRequest {
    private Long movieId;
    private Long profileId;
    private double rating;
    private String comment;
}
