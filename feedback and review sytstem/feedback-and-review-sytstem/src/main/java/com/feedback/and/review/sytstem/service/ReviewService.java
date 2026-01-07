package com.feedback.and.review.sytstem.service;

import com.feedback.and.review.sytstem.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    Review createReview(Review review);
    Page<Review> getAllReviewsForProduct(Long productId, Pageable pageable);
    Double getAverageRatingForProduct(Long productId);
    void deleteReview(Long userId, Long productId);
    boolean hasUserReviewedProduct(Long userId, Long productId);
}
