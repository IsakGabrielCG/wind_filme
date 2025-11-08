package com.example.wind_filmes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "movies")
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Column(nullable=false) private String name;

    @Column(name="release_year") private Integer releaseYear;

    @Column(name="age_rating") private Integer ageRating;

    @Column(columnDefinition="text") private String description;

    @Column(name="duration") private String duration;

    @Column(name="image_url") private String imageUrl;

    @Column(name="trailer_url") private String trailerUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
