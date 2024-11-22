package com.momarket.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private List<OrderItemDTO> orderItems;
}
