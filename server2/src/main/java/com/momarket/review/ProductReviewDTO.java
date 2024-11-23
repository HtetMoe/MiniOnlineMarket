package com.momarket.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductReviewDTO {
    private Long id;
    private int rating;
    private String comment;
    private Long buyerId;  // To associate the review with the buyer
}
