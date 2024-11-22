package com.momarket.user.seller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminSellerController {

    private final SellerService sellerService;
    @PutMapping("/sellers/{id}/approve")
    public ResponseEntity<String> approveSeller(@PathVariable Long id) {
        sellerService.approveSeller(id);
        return ResponseEntity.ok("Seller approved successfully.");
    }

}
