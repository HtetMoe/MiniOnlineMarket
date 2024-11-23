package com.momarket.order;

import com.momarket.cart.Cart;
import com.momarket.cart.CartRepository;
import com.momarket.product.ProductRepository;
import com.momarket.user.buyer.Buyer;
import com.momarket.user.buyer.BuyerRepository;
import com.momarket.user.seller.Seller;
import com.momarket.user.seller.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final SellerService sellerService;
    private final OrderMapper orderMapper;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final BuyerRepository buyerRepository;
    private final OrderRepository orderRepository;

    // Get all orders containing products sold by a specific seller
    @GetMapping("/seller/{id}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersBySeller(@PathVariable Long id) {
        Optional<Seller> sellerOpt = sellerService.getSellerById(id);
        if (!sellerOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Seller seller = sellerOpt.get();

        // Retrieve all orders containing products from this seller
        List<Order> orders = orderService.getOrdersBySeller(seller);

        // Map orders to OrderResponseDTO
        List<OrderResponseDTO> responseDTOs = orderMapper.toResponseDTOList(orders);
        return ResponseEntity.ok(responseDTOs);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        // Get Buyer by ID
        Buyer buyer = buyerRepository.findById(orderRequestDTO.getBuyerId())
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        // Get the Cart
        Cart cart = buyer.getCart();

        // Create the order from cart items
        Order order = orderService.createOrder(cart, buyer);

        // Convert Order entity to OrderResponseDTO using the orderMapper
        OrderResponseDTO responseDTO = orderMapper.toOrderResponseDTO(order);
        return responseDTO;
    }

    //status = SHIPPED, ON_THE_WAY, DELIVERED, or CANCELED
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {

        System.out.println("status : " + status);
        // Fetch the order by ID
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Set the status to the provided status
        order.setStatus(status);

        // Save the updated order
        Order updatedOrder = orderRepository.save(order);

        // Map the updated order to OrderResponseDTO using the builder pattern
        OrderResponseDTO orderResponseDTO = OrderResponseDTO.builder()
                .id(updatedOrder.getId())
                .status(updatedOrder.getStatus())
                .totalAmount(updatedOrder.getTotalAmount())
                .orderItems(updatedOrder.getOrderItems().stream()
                        .map(orderItem -> OrderItemDTO.builder()
                                //.productId(orderItem.getProduct().getId()) // Add productId if needed
                                .id(orderItem.getId())
                                .productName(orderItem.getProduct().getName())
                                .quantity(orderItem.getQuantity())
                                .price(orderItem.getSubtotal())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        // Return the response with the updated order
        return ResponseEntity.ok(orderResponseDTO);
    }



    @GetMapping("/buyers/{id}/orders")
    public List<OrderResponseDTO> getOrdersByBuyer(@PathVariable Long id) {
        // Fetch orders for the given buyer ID
        List<Order> orders = orderService.getOrdersByBuyer(id);

        // Map the Order entities to OrderResponseDTO using Builder
        return orders.stream()
                .map(order -> OrderResponseDTO.builder()
                        .id(order.getId())
                        .status(order.getStatus())
                        .totalAmount(order.getTotalAmount())
                        .orderItems(order.getOrderItems().stream()
                                .map(orderItem -> OrderItemDTO.builder()
                                        //.productId(orderItem.getProduct().getId())
                                        .id(orderItem.getId())
                                        .productName(orderItem.getProduct().getName())
                                        .quantity(orderItem.getQuantity())
                                        .price(orderItem.getSubtotal())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        // Fetch the order by ID
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Delete the order from the repository
        orderRepository.delete(order);

        // Return a response indicating successful deletion
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }


}
