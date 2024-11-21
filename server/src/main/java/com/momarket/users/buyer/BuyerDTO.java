package com.momarket.users.buyer;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BuyerDTO {
    private Long buyerId;
    private String name;
    private String email;
    private String address;
}
