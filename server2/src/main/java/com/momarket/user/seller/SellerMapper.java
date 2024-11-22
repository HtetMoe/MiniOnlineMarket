package com.momarket.user.seller;

import com.momarket.user.ApprovalStatus;
import com.momarket.user.Role;
import com.momarket.user.User;
import com.momarket.user.seller.dto.CreateSellerDTO;
import org.springframework.stereotype.Component;

@Component
public class SellerMapper {

    /**
     * Maps CreateSellerDTO to a User entity.
     *
     * @param dto The CreateSellerDTO containing user details.
     * @return A new User entity.
     */
    public User toUser(CreateSellerDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword()) // Ensure password is encrypted at the service layer
                .role(Role.SELLER)
                .isActive(true)
                .build();
    }

    /**
     * Maps CreateSellerDTO and User entity to a Seller entity.
     *
     * @param user The associated User entity.
     * @return A new Seller entity.
     */
    public Seller toSeller(User user) {
        return Seller.builder()
                .user(user)
                .status(ApprovalStatus.PENDING)
                .build();
    }
}
