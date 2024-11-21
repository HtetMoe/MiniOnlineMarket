//package com.momarket.users.auth.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//    private final AuthenticationProvider authenticationProvider;
//    private final JwtAuthFilter jwtAuthenticationFilter;
//
//    public WebSecurityConfig(
//            JwtAuthFilter jwtAuthenticationFilter,
//            AuthenticationProvider authenticationProvider
//    ) {
//        this.authenticationProvider = authenticationProvider;
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // Disable CSRF protection
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/api/v1/auth/**").permitAll()  // Allow access to "/auth/**" without authentication
//                        .requestMatchers("/api/v1/sellers/**").permitAll()  // Allow access to "/auth/**" without authentication
//                        .anyRequest().authenticated()  // Require authentication for all other requests
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless session policy (JWT, no session)
//                )
//                .authenticationProvider(authenticationProvider)  // Use custom authentication provider
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // Add JWT filter before UsernamePasswordAuthenticationFilter
//
//        return http.build();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.setAllowedOrigins(List.of("http://localhost:8080"));
//        configuration.setAllowedMethods(List.of("GET","POST"));
//        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        source.registerCorsConfiguration("/**",configuration);
//
//        return source;
//    }
//}
