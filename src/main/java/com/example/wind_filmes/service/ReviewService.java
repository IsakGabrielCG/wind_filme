package com.example.wind_filmes.service;

import com.example.wind_filmes.entity.Movie;
import com.example.wind_filmes.entity.Profile;
import com.example.wind_filmes.entity.Review;
import com.example.wind_filmes.repository.MovieRepository;
import com.example.wind_filmes.repository.ProfileRepository;
import com.example.wind_filmes.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final ProfileRepository profileRepository;

    public Review createReview(Long movieId, Long profileId, double rating, String comment) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        Review review = new Review();
        review.setMovie(movie);
        review.setProfile(profile);
        review.setRating(rating);
        review.setComment(comment);

        return reviewRepository.save(review);
    }

    public List<Review> listAll() {
        return reviewRepository.findAll();
    }

    public List<Review> listByMovie(Long movieId) {
        return reviewRepository.findByMovieId(movieId);
    }

    public List<Review> listByProfile(Long profileId) {
        return reviewRepository.findByProfileId(profileId);
    }

    public Review updateReview(Long reviewId, double rating, String comment) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review não encontrada"));

        review.setRating(rating);
        review.setComment(comment);

        return reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review não encontrada"));

        reviewRepository.delete(review);
    }

}
