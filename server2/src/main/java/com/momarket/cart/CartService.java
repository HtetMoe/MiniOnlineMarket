package com.momarket.cart;

import com.momarket.user.buyer.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final BuyerRepository buyerRepository;
    private final CartMapper cartMapper;

    // Fetch cart for a given buyer ID
    public CartDTO getCartByBuyerId(Long buyerId) {
        return buyerRepository.findById(buyerId)
                .map(buyer -> cartMapper.toCartDTO(buyer.getCart())) // Map the Cart entity to CartDTO
                .orElse(null); // Return null if the buyer does not exist
    }
}
