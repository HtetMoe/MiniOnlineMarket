package com.momarket.users.seller;

import org.springframework.stereotype.Service;

@Service
public class SellerMapper {

    public SellerResponseDTO from(Seller seller){
        return  SellerResponseDTO
                .builder()
                .id(seller.getSellerId())
                .email(seller.getUser().getEmail())
                .fullName(seller.getUser().getFullName())
                .role(seller.getUser().getRole())
                .isApproved(seller.getIsApproved())
                .products(seller.getProducts())
                .build();
    }
}
