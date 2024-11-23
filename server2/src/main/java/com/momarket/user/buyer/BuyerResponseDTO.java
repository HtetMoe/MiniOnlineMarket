package com.momarket.user.buyer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerResponseDTO {
    private String email;
    private String password;
    private String role;
    private String username;
}
