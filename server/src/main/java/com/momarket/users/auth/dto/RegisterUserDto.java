package com.momarket.users.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
public class RegisterUserDto {
    private String email;

    private String password;

    private String fullName;

}