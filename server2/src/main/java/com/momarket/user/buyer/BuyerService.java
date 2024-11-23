package com.momarket.user.buyer;
import com.momarket.cart.Cart;
import com.momarket.cart.CartRepository;
import com.momarket.user.Role;
import com.momarket.user.User;
import com.momarket.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class BuyerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BuyerMapper buyerMapper;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    public BuyerResponseDTO registerBuyer(BuyerDTO buyerDTO) {
        // Create User entity from BuyerDTO
        User user = new User();
        user.setEmail(buyerDTO.getEmail());
        user.setName(buyerDTO.getUsername());
        //user.setPassword(passwordEncoder.encode(buyerDTO.getPassword()));
        user.setPassword(buyerDTO.getPassword());
        user.setRole(Role.BUYER);

        // Save the User
        User savedUser = userRepository.save(user);

        // Create Buyer entity and link to User
        Buyer buyer = new Buyer();
        buyer.setUser(savedUser);
        buyer.setShippingAddress(buyerDTO.getShippingAddress());
        buyer.setBillingAddress(buyerDTO.getBillingAddress());

        // Create an empty Cart for the Buyer
        Cart cart = new Cart();
        //cart.setBuyer(buyer);
        cartRepository.save(cart);

        // Set the Cart for Buyer and save the Buyer
        buyer.setCart(cart);
        buyerRepository.save(buyer);

        // Return the response DTO
        return buyerMapper.toBuyerResponseDTO(buyer);
    }
}

