package com.momarket.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CartDTO {
    private Long cartId;    // Cart ID
    private List<CartItemDTO> items;  // List of cart items, each represented by CartItemDTO
}
