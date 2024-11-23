package com.momarket.product;

import com.momarket.users.User;
import com.momarket.users.seller.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySeller(Seller seller);
}
