package com.example.wind_filmes.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponse {
    private Long id;
    private String name;
    private String language;
    private boolean kid;
    private Long userId;
}
