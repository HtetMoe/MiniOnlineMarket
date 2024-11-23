//package com.momarket.users.auth;
//
//import com.momarket.users.User;
//import com.momarket.users.UserRepository;
//import com.momarket.users.auth.dto.LoginUserDto;
//import com.momarket.users.auth.dto.RegisterUserDto;
//import com.momarket.users.seller.Seller;
//import com.momarket.users.seller.SellerService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@RequiredArgsConstructor
//@Service
//public class AuthService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final RoleRepository roleRepository;
//    private final AuthenticationManager authenticationManager;
//    private final SellerService sellerService;  // Injecting SellerService to create sellers
//
//    // User signup logic
//    public User signup(RegisterUserDto input) {
//        Set<Role> roles = input.getRoles();
//
//        // Save roles if they aren't already in the database
//        for (Role role : roles) {
//            if (role.getId() == null) {  // New role doesn't have an ID, so save it
//                roleRepository.save(role);
//            }
//        }
//
//        // Create the User object and set the roles
//        User user = User.builder()
//                .fullName(input.getFullName())
//                .email(input.getEmail())
//                .password(passwordEncoder.encode(input.getPassword()))
//                .roles(roles)  // Set roles for the user
//                .build();
//
//        // Save the user
//        user = userRepository.save(user);
//
//        // If the user has the SELLER role, register them as a seller
//        if (roles.stream().anyMatch(role -> role.getName().equalsIgnoreCase("SELLER"))) {
//            Seller seller = new Seller();
//            seller.setUser(user);  // Associate the seller with the user
//            sellerService.registerSeller(seller);  // Register the seller in the seller service
//        }
//
//        return user;
//    }
//
//    // User authentication logic
//    public User authenticate(LoginUserDto input) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        input.getEmail(),
//                        input.getPassword()
//                )
//        );
//
//        return userRepository.findByEmail(input.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
//}
