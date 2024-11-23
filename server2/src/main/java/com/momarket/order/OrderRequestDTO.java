package com.momarket.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequestDTO {
    private Long buyerId;  // Buyer ID
    private List<OrderItemDTO> items;
}
