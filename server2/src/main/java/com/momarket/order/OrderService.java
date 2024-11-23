package com.momarket.order;

import com.momarket.cart.Cart;
import com.momarket.user.buyer.Buyer;
import com.momarket.user.seller.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

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

    public Order createOrder(Cart cart, Buyer buyer) {
        // Create a new Order entity
        Order order = new Order();
        order.setBuyer(buyer);
        order.setStatus(OrderStatus.CONFIRMED);

        // Convert CartItems to OrderItems
        List<OrderItem> orderItems = cart.getCartItems().stream()
                .map(cartItem -> {
                    // Convert CartItem to OrderItem
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setSubtotal(cartItem.getSubtotal());
                    orderItem.setSubtotal(cartItem.getSubtotal().multiply(new BigDecimal(cartItem.getQuantity())));
                    orderItem.setOrder(order);  // Associate the Order with the OrderItem
                    return orderItem;
                })
                .collect(Collectors.toList());

        // Set the order items to the Order
        order.setOrderItems(orderItems);

        // Calculate and set the total amount
        order.setTotalAmount(calculateTotalAmount(orderItems));

        // Save the order and return it
        return orderRepository.save(order);
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItem::getSubtotal)  // Get the subtotal for each OrderItem
                .reduce(BigDecimal.ZERO, BigDecimal::add);  // Sum the subtotals using BigDecimal
    }

    public List<Order> getOrdersByBuyer(Long buyerId) {
        return orderRepository.findByBuyerId(buyerId);
    }
}
