package com.momarket.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class CartItemDTO {
    private Long productId;   // Product ID
    private String productName;   // Product name
    private int quantity;   // Quantity of the product in the cart
    private BigDecimal price;   // Price of the product
}
