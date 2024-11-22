package com.momarket.order;

import com.momarket.user.seller.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    // Check if a product has been ordered (exists in any OrderItem)
    public boolean hasOrdersForProduct(Long productId) {
        // Find all OrderItems associated with the given productId
        List<OrderItem> orderItems = orderItemRepository.findByProductId(productId);

        // If any OrderItems are found, it means the product has been purchased
        return !orderItems.isEmpty();
    }

    public List<Order> getOrdersBySeller(Seller seller) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getOrderItems().stream()
                        .anyMatch(orderItem -> orderItem.getProduct().getSeller().equals(seller)))
                .toList();
    }
}
