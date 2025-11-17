package com.example.wind_filmes.repository;

import com.example.wind_filmes.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovieId(Long movieId);
    List<Review> findByProfileId(Long profileId);
}

