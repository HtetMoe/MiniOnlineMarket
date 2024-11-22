package com.momarket.order;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private String productName;
    private String sellerName;
    private Integer quantity;
    private BigDecimal price;
}
