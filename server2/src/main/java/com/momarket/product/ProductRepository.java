package com.momarket.product;

import com.momarket.user.seller.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySeller(Seller seller);  // Find products by seller
    Optional<Product> findById(Long id);  // Find product by ID
}
