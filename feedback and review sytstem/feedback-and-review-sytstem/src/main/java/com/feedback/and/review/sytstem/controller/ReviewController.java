package com.feedback.and.review.sytstem.controller;

import com.feedback.and.review.sytstem.entity.Review;
import com.feedback.and.review.sytstem.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody Review review) {
        try {
            Review createdReview = reviewService.createReview(review);
            return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Page<Review>> getAllReviewsForProduct(
        @PathVariable Long productId,
        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Review> reviews = reviewService.getAllReviewsForProduct(productId, pageable);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}/average-rating")
    public ResponseEntity<Double> getAverageRatingForProduct(@PathVariable Long productId) {
        Double averageRating = reviewService.getAverageRatingForProduct(productId);
        if (averageRating == null) {
            return new ResponseEntity<>(0.0, HttpStatus.OK);
        }
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteReview(@RequestParam Long userId, @RequestParam Long productId) {
        try {
            reviewService.deleteReview(userId, productId);
            return new ResponseEntity<>("Review deleted successfully.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> hasUserReviewedProduct(@RequestParam Long userId, @RequestParam Long productId) {
        boolean hasReviewed = reviewService.hasUserReviewedProduct(userId, productId);
        return new ResponseEntity<>(hasReviewed, HttpStatus.OK);
    }
}
