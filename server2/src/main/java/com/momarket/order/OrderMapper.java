package com.momarket.order;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderResponseDTO toResponseDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getOrderItems().stream()
                        .map(this::toOrderItemDTO)
                        .collect(Collectors.toList())
        );
    }

    public List<OrderResponseDTO> toResponseDTOList(List<Order> orders) {
        return orders.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getProduct().getName(),
                orderItem.getProduct().getSeller().getUser().getName(),
                orderItem.getQuantity(),
                orderItem.getSubtotal()
        );
    }
}
