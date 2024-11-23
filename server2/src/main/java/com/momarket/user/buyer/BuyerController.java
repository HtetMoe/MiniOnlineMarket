package com.momarket.user.buyer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/buyers")
@RequiredArgsConstructor
public class BuyerController {
    private final BuyerService buyerService;

    @PostMapping("/")
    public ResponseEntity<BuyerResponseDTO> registerBuyer(@RequestBody BuyerDTO buyerDTO) {
        BuyerResponseDTO buyerResponseDTO = buyerService.registerBuyer(buyerDTO);
        return ResponseEntity.ok(buyerResponseDTO);
    }
}
