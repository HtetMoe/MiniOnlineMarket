package com.momarket.product;

import com.momarket.users.seller.Seller;
import com.momarket.users.seller.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    // Create a new product
    public Product createProduct(Product product) {
        // Here you might want to check if the seller is valid (e.g., is approved)
        return productRepository.save(product);
    }

    // Update an existing product's details
    public Product updateProduct(Product product) {
        // You can add additional checks, like ensuring a product is not already purchased
        return productRepository.save(product);
    }

    // Get all products associated with a particular seller
    public List<Product> getProductsBySeller(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new IllegalArgumentException("Seller not found"));
        return productRepository.findBySeller(seller);
    }

    // Get a product by its ID
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    // Delete a product by its ID
    public void deleteProduct(Long productId) {
        Product product = getProductById(productId);
        // Additional business logic could go here, such as checking if it's part of an order
        productRepository.delete(product);
    }

    // Check if the product is purchasable (i.e., stock > 0)
    public boolean isProductPurchasable(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return product.getStockQuantity() > 0;
    }
}
