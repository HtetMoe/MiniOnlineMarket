package com.momarket.users.seller;

import com.momarket.product.Product;
import com.momarket.product.ProductDTO;
import com.momarket.product.ProductMapper;  // Assuming you have a ProductMapper
import com.momarket.users.User;
import com.momarket.users.UserService;
import com.momarket.users.auth.dto.RegisterUserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private ProductMapper productMapper;  // Assuming there's a ProductMapper

    @Autowired
    private UserService userService;  // Inject UserService correctly
    @Autowired
    private ModelMapper modelMapper;

    // Register a new seller (creates a User and then Seller)
    @PostMapping("/")
    public ResponseEntity<SellerResponseDTO> registerSeller(@RequestBody RegisterUserDto registerUserDto) {
        // Assuming user details are included in the Seller request
        User user = User.builder()
                .email(registerUserDto.getEmail())
                .fullName(registerUserDto.getFullName())
                .password(registerUserDto.getPassword())
                .role("SELLER")
                .build();

        // Check if the user already exists
        Optional<User> existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body(null);  // User already exists
        }

        // Register the user first
        User createdUser = userService.createUser(user);

        // After the user is created, associate it with the Seller
        Seller seller = new Seller();
        seller.setUser(createdUser);
        seller.setIsApproved(false);  // Default to unapproved
        seller.setCreatedAt(new java.util.Date());

        Seller createdSeller = sellerService.registerSeller(seller);

        return ResponseEntity.ok(
                sellerMapper.from(createdSeller)
        );
    }

    // Get a seller by ID
    @GetMapping("/{sellerId}")
    public ResponseEntity<SellerResponseDTO> getSellerById(@PathVariable Long sellerId) {
        Optional<Seller> seller = sellerService.getSellerById(sellerId);
        return seller
                .map(s -> ResponseEntity.ok(sellerMapper.from(s)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get products of a seller by their ID
    @GetMapping("/{sellerId}/products")
    public ResponseEntity<List<ProductDTO>> getSellerProducts(@PathVariable Long sellerId) {
        Optional<Seller> seller = sellerService.getSellerById(sellerId);
        if (seller.isPresent()) {
            // Get the list of products for the seller
            List<Product> products = seller.get().getProducts();

            // Map products to DTOs (using ProductMapper)
            List<ProductDTO> productDTOs = products.stream()
                    .map(productMapper::from)  // Assuming 'from' is the method to convert Product to ProductDTO
                    .collect(Collectors.toList());

            return ResponseEntity.ok(productDTOs);
        } else {
            return ResponseEntity.notFound().build();  // Return 404 if seller not found
        }
    }


}
