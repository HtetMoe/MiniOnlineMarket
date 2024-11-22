
package com.momarket.order;

import com.momarket.user.seller.Seller;
import com.momarket.user.seller.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private OrderMapper orderMapper;

    // Get all orders containing products sold by a specific seller
    @GetMapping("/seller/{id}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersBySeller(@PathVariable Long id) {
        // Verify the seller exists
        Optional<Seller> sellerOpt = sellerService.getSellerById(id);
        if (!sellerOpt.isPresent()) {
            return ResponseEntity.notFound().build(); // Seller not found
        }

        Seller seller = sellerOpt.get();

        // Retrieve all orders containing products from this seller
        List<Order> orders = orderService.getOrdersBySeller(seller);

        // Map orders to OrderResponseDTO
        List<OrderResponseDTO> responseDTOs = orderMapper.toResponseDTOList(orders);

        return ResponseEntity.ok(responseDTOs);
    }
}
