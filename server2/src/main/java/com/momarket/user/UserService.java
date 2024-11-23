package com.momarket.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder; // Uncomment if you need password encryption.

    public User saveUser(User user) {
        // If password encryption is required, uncomment the following lines
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }


    public User updateUser(Long id, String name, String email, String password) {
        User user = getUserById(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
//        if (password != null && !password.isEmpty()) {
//            user.setPassword(passwordEncoder.encode(password));
//        }
        return userRepository.save(user);
    }
}
