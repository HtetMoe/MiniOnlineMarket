package com.momarket.order;

import com.momarket.cart.CartItem;
import com.momarket.cart.CartItemDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    // Convert Order to OrderResponseDTO
    public OrderResponseDTO toOrderResponseDTO(Order order) {
        // Map OrderItems from Order to OrderItemDTO list
        List<OrderItemDTO> orderItems = order.getOrderItems().stream()
                .map(this::toOrderItemDTO)
                .collect(Collectors.toList());

        return OrderResponseDTO.builder()
                .id(order.getId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .orderItems(orderItems)
                .build();
    }

    // Convert OrderItem to OrderItemDTO
    private OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                //.productId(orderItem.getProduct().getId())
                .productName(orderItem.getProduct().getName())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getSubtotal())
                .build();
    }

    // Convert a list of Orders to OrderResponseDTO list
    public List<OrderResponseDTO> toResponseDTOList(List<Order> orders) {
        return orders.stream()
                .map(this::toOrderResponseDTO)
                .collect(Collectors.toList());
    }
}
