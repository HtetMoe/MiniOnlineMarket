package com.momarket.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // Custom query to find OrderItems by ProductId
    List<OrderItem> findByProductId(Long productId);
}
