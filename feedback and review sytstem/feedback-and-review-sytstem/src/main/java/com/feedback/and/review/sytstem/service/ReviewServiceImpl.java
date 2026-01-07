package com.feedback.and.review.sytstem.service;

import com.feedback.and.review.sytstem.entity.Review;
import com.feedback.and.review.sytstem.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review createReview(Review review) {
        Optional<Review> existingReview = reviewRepository.findByUserIdAndProductId(review.getUserId(), review.getProductId());
        if (existingReview.isPresent()) {
            throw new RuntimeException("User has already reviewed this product.");
        }
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviewsForProduct(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public Page<Review> getAllReviewsForProduct(Long productId, Pageable pageable) {
        return reviewRepository.findByProductId(productId, pageable);
    }

    @Override
    public Double getAverageRatingForProduct(Long productId) {
        return reviewRepository.getAverageRatingByProductId(productId);
    }

    @Override
    public void deleteReview(Long userId, Long productId) {
        Optional<Review> review = reviewRepository.findByUserIdAndProductId(userId, productId);
        if (review.isPresent()) {
            reviewRepository.delete(review.get());
        } else {
            throw new RuntimeException("Review not found for this user and product.");
        }
    }

    @Override
    public boolean hasUserReviewedProduct(Long userId, Long productId) {
        return reviewRepository.findByUserIdAndProductId(userId, productId).isPresent();
    }
}
