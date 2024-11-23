package com.momarket.order;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper; // Inject ModelMapper

    // Place an order
    public OrderDTO placeOrder(OrderDTO orderDTO) {
        // Convert DTO to Entity
        Order order = modelMapper.map(orderDTO, Order.class);

        // Optionally, handle additional logic like calculating total amount if not present in DTO
        order.setOrderStatus(OrderStatus.PENDING);

        Order savedOrder = orderRepository.save(order);

        // Convert the saved Order entity back to DTO
        return modelMapper.map(savedOrder, OrderDTO.class);
    }

    // Get orders by buyer ID
    public List<OrderDTO> getOrdersByBuyer(Long buyerId) {
        List<Order> orders = orderRepository.findByBuyer_BuyerId(buyerId);

        // Convert each Order entity to OrderDTO using ModelMapper
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    // Update order status
    public OrderDTO updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus(orderStatus);
        Order updatedOrder = orderRepository.save(order);

        // Return the updated order as DTO
        return modelMapper.map(updatedOrder, OrderDTO.class);
    }
}
