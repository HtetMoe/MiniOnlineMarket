package com.momarket.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query method to find orders by buyerId
    List<Order> findByBuyerId(Long buyerId);
}
