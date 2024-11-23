package com.momarket.users.seller;

import com.momarket.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    // Find seller by the associated User's email
    Optional<Seller> findByUserEmail(String email);

    // Find sellers by approval status
    List<Seller> findByIsApproved(Boolean isApproved);

    Optional<Seller> findByUser(User user);

    // You can also add other custom queries, for example:
    // List<Seller> findByUserFullNameContaining(String fullName);
}
