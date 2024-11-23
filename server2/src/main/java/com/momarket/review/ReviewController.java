package com.momarket.review;

import com.momarket.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/product/{productId}")
    public ResponseEntity<ProductReviewDTO> writeReview(@PathVariable Long productId, @RequestBody ProductReviewDTO reviewDTO) {
        // Submit the review through the service layer
        ProductReviewDTO savedReview = reviewService.submitReview(productId, reviewDTO);

        // Return the saved review
        return ResponseEntity.ok(savedReview);
    }

    // Delete review by ID (only ADMIN can delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        // Get the currently authenticated user
        //User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Check if the user is an ADMIN
        //if (currentUser.getRole() != Role.ADMIN) {
            //return ResponseEntity.status(403).build(); // Forbidden if the user is not ADMIN
        //}

        // Call the service to delete the review
        reviewService.deleteReview(id);

        // Return HTTP 204 No Content indicating successful deletion
        return ResponseEntity.noContent().build();
    }
}

