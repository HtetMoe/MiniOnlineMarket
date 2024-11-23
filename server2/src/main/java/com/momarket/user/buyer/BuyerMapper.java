package com.momarket.user.buyer;
import com.momarket.user.Role;
import com.momarket.user.User;
import org.springframework.stereotype.Component;

@Component
public class BuyerMapper {

    // Manually map Buyer entity to BuyerResponseDTO
    public BuyerResponseDTO toBuyerResponseDTO(Buyer buyer) {
        // Manually mapping fields
        return new BuyerResponseDTO(
                buyer.getUser().getEmail(), // Mapping email from User entity
                buyer.getUser().getPassword(), // Mapping password from User entity
                buyer.getUser().getRole().name(), // Mapping role from User entity
                buyer.getUser().getName() // Mapping username from User entity
        );
    }

    // Manually map BuyerDTO to Buyer entity
    public Buyer toBuyerEntity(BuyerDTO buyerDTO) {
        // Manually mapping fields
        User user = new User();
        user.setEmail(buyerDTO.getEmail());
        user.setName(buyerDTO.getUsername());
        user.setPassword(buyerDTO.getPassword());  // You should encode the password before saving
        user.setRole(Role.BUYER);

        Buyer buyer = new Buyer();
        buyer.setUser(user);
        buyer.setShippingAddress(buyerDTO.getShippingAddress());
        buyer.setBillingAddress(buyerDTO.getBillingAddress());

        return buyer;
    }
}

