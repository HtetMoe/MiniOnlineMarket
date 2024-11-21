package com.momarket.users.buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buyers")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    // Register a new buyer
    @PostMapping("/register")
    public ResponseEntity<BuyerDTO> registerBuyer(@RequestBody BuyerDTO buyerDTO) {
        BuyerDTO registeredBuyer = buyerService.registerBuyer(buyerDTO);
        return ResponseEntity.ok(registeredBuyer);
    }

    // Get buyer details by ID
    @GetMapping("/{buyerId}")
    public ResponseEntity<BuyerDTO> getBuyerById(@PathVariable Long buyerId) {
        BuyerDTO buyerDTO = buyerService.getBuyerById(buyerId);
        return ResponseEntity.ok(buyerDTO);
    }

    // Update buyer details
    @PutMapping("/{buyerId}")
    public ResponseEntity<BuyerDTO> updateBuyer(@PathVariable Long buyerId, @RequestBody BuyerDTO buyerDTO) {
        BuyerDTO updatedBuyer = buyerService.updateBuyer(buyerId, buyerDTO);
        return ResponseEntity.ok(updatedBuyer);
    }

    // Delete a buyer
    @DeleteMapping("/{buyerId}")
    public ResponseEntity<Void> deleteBuyer(@PathVariable Long buyerId) {
        buyerService.deleteBuyer(buyerId);
        return ResponseEntity.noContent().build();
    }
}
