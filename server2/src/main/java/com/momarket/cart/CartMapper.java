package com.momarket.cart;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartMapper {

    // Manually map Cart entity to CartDTO using the builder pattern
    public CartDTO toCartDTO(Cart cart) {
        // Using the builder pattern to create CartDTO and CartItemDTO
        return CartDTO.builder()
                .cartId(cart.getId())
                //.buyerId(cart.getBuyer() != null ? cart.getBuyer().getId() : null)  // Assuming Cart has a relation to Buyer
                .items(cart.getCartItems().stream()
                        .map(cartItem -> CartItemDTO.builder()
                                .productId(cartItem.getProduct().getId())
                                .productName(cartItem.getProduct().getName())
                                .quantity(cartItem.getQuantity())
                                .price(cartItem.getProduct().getPrice())  // Assuming price is a BigDecimal in Product
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}

