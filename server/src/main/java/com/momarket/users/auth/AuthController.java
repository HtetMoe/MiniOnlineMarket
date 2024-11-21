//package com.momarket.users.auth;
//
//import com.momarket.users.User;
//import com.momarket.users.auth.config.JwtService;
//import com.momarket.users.auth.dto.LoginResponse;
//import com.momarket.users.auth.dto.LoginUserDto;
//import com.momarket.users.auth.dto.RegisterResponse;
//import com.momarket.users.auth.dto.RegisterUserDto;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequestMapping("/api/v1/auth")
//@RestController
//public class AuthController {
//    private final JwtService jwtService;
//
//    private final AuthService authenticationService;
//
//    public AuthController(JwtService jwtService, AuthService authenticationService) {
//        this.jwtService = jwtService;
//        this.authenticationService = authenticationService;
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterUserDto registerUserDto) {
//        User registeredUser = authenticationService.signup(registerUserDto);
//        RegisterResponse userResponse = RegisterResponse.builder()
//                .roles(registeredUser.getRoles())
//                .id(registeredUser.getId())
//                .email(registeredUser.getEmail())
//                .fullName(registeredUser.getFullName())
//                .build();
//        return ResponseEntity.ok(userResponse);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
//        User authenticatedUser = authenticationService.authenticate(loginUserDto);
//
//        String jwtToken = jwtService.generateToken(authenticatedUser);
//
//        LoginResponse loginResponse = LoginResponse.builder()
//                .token(jwtToken)
//                .expiresIn(jwtService.getExpirationTime())
//                .roles(authenticatedUser.getRoles())
//                .build();
//
//        return ResponseEntity.ok(loginResponse);
//    }
//}
//
