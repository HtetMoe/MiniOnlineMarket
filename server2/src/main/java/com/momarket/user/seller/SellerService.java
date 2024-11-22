package com.momarket.user.seller;

import com.momarket.user.ApprovalStatus;
import com.momarket.user.User;
import com.momarket.user.UserService;
import com.momarket.user.seller.dto.CreateSellerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    private final UserService userService;

    public void createSeller(CreateSellerDTO createSellerDTO) {
        // Map DTO to User
        User user = sellerMapper.toUser(createSellerDTO);

        // Save User (with password encryption handled in UserService)
        userService.saveUser(user);

        // Map User to Seller
        Seller seller = sellerMapper.toSeller(user);

        // Save Seller
        sellerRepository.save(seller);
    }

    public void approveSeller(Long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + id));

        seller.setStatus(ApprovalStatus.APPROVED); // Update the approval status
        sellerRepository.save(seller); // Save the updated seller
    }

    public Optional<Seller> getSellerById(Long sellerId) {
        // Query the SellerRepository to find a seller by their ID
        return sellerRepository.findById(sellerId);
    }
}

