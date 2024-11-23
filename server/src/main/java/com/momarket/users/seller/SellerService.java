package com.momarket.users.seller;

import com.momarket.users.User;
import com.momarket.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final UserService userService;  // Correct injection of UserService

    // Register a new seller
    public Seller registerSeller(Seller seller) {
        // Check if a user with the same email already exists
        // Associate the created user with the seller
        seller.setCreatedAt(new Date());
        seller.setUpdatedAt(new Date());
        seller.setIsApproved(false); // New seller needs admin approval

        return sellerRepository.save(seller);  // Save the seller
    }

    // Find seller by ID
    public Optional<Seller> getSellerById(Long sellerId) {
        return sellerRepository.findById(sellerId);
    }

    // Find seller by email (for login purposes)
    public Optional<Seller> getSellerByEmail(String email) {
        return sellerRepository.findByUserEmail(email); // Now using the findByUserEmail query
    }

    // Approve a seller (admin approval)
    public Seller approveSeller(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        seller.setIsApproved(true);
        seller.setUpdatedAt(new Date());
        return sellerRepository.save(seller);  // Use the save method provided by JPA repository
    }

    // Get all sellers (e.g., for admin view)
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Optional<Seller> getSellerByUser(User user) {
        return sellerRepository.findByUser(user);  // Using the repository method to fetch seller by User
    }

    // Additional seller-related logic (e.g., update details) can be added here
}
