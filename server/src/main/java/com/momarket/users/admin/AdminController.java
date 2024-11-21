package com.momarket.users.admin;

import com.momarket.users.seller.Seller;
import com.momarket.users.seller.SellerMapper;
import com.momarket.users.seller.SellerResponseDTO;
import com.momarket.users.seller.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")

public class AdminController {


    @Autowired
    private SellerService sellerService;

    @Autowired
    private SellerMapper sellerMapper;

    @GetMapping("/sellers")
    public ResponseEntity<List<SellerResponseDTO>> getAllSellers() {
        List<Seller> sellers = sellerService.getAllSellers();
        return ResponseEntity.ok(sellers.stream().map(seller -> sellerMapper.from(seller)).toList());
    }

    // Approve a seller (Admin action)
    @PutMapping("/{sellerId}/approve")
    public ResponseEntity<SellerResponseDTO> approveSeller(@PathVariable Long sellerId) {
        Optional<Seller> seller = sellerService.getSellerById(sellerId);

        if (seller.isPresent()) {
            Seller approvedSeller = seller.get();
            sellerService.approveSeller(approvedSeller.getSellerId());  // Save the updated seller (corrected to registerSeller)
            return ResponseEntity.ok( sellerMapper.from(approvedSeller));
        }

        return ResponseEntity.notFound().build();
    }
}
