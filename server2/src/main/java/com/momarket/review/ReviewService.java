package com.momarket.review;

import com.momarket.product.Product;
import com.momarket.product.ProductRepository;
import com.momarket.user.buyer.Buyer;
import com.momarket.user.buyer.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final BuyerRepository buyerRepository;

    // Submit the review for a product
    public ProductReviewDTO submitReview(Long productId, ProductReviewDTO reviewDTO) {
        // Validate the product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Validate the buyer
        Buyer buyer = buyerRepository.findById(reviewDTO.getBuyerId())
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        // Create a new review entity using the builder pattern
        Review review = Review.builder()
                .product(product)
                .buyer(buyer)
                .rating(reviewDTO.getRating())
                .comment(reviewDTO.getComment())
                .isApproved(true) // Assuming approval logic is handled here
                .build();

        // Save the review to the database
        review = reviewRepository.save(review);

        // Return the review as a DTO using the builder pattern
        return ProductReviewDTO.builder()
                .id(review.getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .buyerId(buyer.getId())
                .build();
    }

    // Delete a review by ID
    public void deleteReview(Long id) {
        // Check if the review exists
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        // Delete the review
        reviewRepository.delete(review);
    }
}
