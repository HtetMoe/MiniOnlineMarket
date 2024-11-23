package com.momarket.user.seller;

import com.momarket.user.seller.dto.CreateSellerDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @PostMapping
    public ResponseEntity<String> createSeller(@RequestBody @Valid CreateSellerDTO createSellerDTO) {
        sellerService.createSeller(createSellerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Seller created successfully and awaiting approval.");
    }

}
