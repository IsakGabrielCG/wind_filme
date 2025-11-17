package com.example.wind_filmes.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequest {
    private String name;
    private String language;
    private boolean kid;
}
