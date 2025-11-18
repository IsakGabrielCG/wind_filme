package com.example.wind_filmes.controller;

import com.example.wind_filmes.dto.request.ReviewRequest;
import com.example.wind_filmes.dto.request.ReviewUpdateRequest;
import com.example.wind_filmes.entity.Review;
import com.example.wind_filmes.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> create(@RequestBody ReviewRequest request) {

        Review review = reviewService.createReview(
                request.getMovieId(),
                request.getProfileId(),
                request.getRating(),
                request.getComment()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(review);
    }

    @GetMapping
    public ResponseEntity<List<Review>> listAll() {
        return ResponseEntity.ok(reviewService.listAll());
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Review>> listByMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(reviewService.listByMovie(movieId));
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<Review>> listByProfile(@PathVariable Long profileId) {
        return ResponseEntity.ok(reviewService.listByProfile(profileId));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewUpdateRequest dto
    ) {
        Review updated = reviewService.updateReview(
                reviewId,
                dto.getRating(),
                dto.getComment()
        );

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Long reviewId
    ) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

}
