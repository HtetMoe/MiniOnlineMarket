package com.momarket.order;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderDTO {

    @NotNull
    private Long buyerId;
    private List<OrderItemDTO> orderItems;
    private OrderStatus orderStatus;
    private Double totalAmount;
    private String shippingAddress;
    private String billingAddress;
    private String paymentStatus;

    // Getters and Setters
}
