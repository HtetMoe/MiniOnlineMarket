package com.momarket.users.seller;

import com.momarket.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerResponseDTO {

    private Long id;

    private String email;

    private String fullName;

    private String role;

    private Boolean isApproved;

    private List<Product> products;
}
