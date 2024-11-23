package com.momarket.user.buyer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerDTO {
    private String email;
    private String password;
    private String role;
    private String username;
    private String shippingAddress;
    private String billingAddress;
}
