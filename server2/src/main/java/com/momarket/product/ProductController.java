package com.momarket.product;

import com.momarket.order.OrderService;
import com.momarket.product.category.Category;
import com.momarket.product.category.CategoryService;
import com.momarket.user.ApprovalStatus;
import com.momarket.user.seller.Seller;
import com.momarket.user.seller.SellerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SellerService sellerService;

    // Create a new product for a specific seller
    @PostMapping("/seller/{sellerId}")
    @Transactional
    public ResponseEntity<ProductResponseDTO> createProduct(@PathVariable Long sellerId, @RequestBody CreateProductDTO createProductDTO) {
        Optional<Seller> seller = sellerService.getSellerById(sellerId);
        if (!seller.isPresent()) {
            return ResponseEntity.notFound().build(); // Seller not found
        }

        if (!seller.get().getStatus().equals(ApprovalStatus.APPROVED)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Seller not approved
        }

        // Ensure category exists
        Category category = categoryService.getCategoryById(createProductDTO.getCategoryId())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setId(createProductDTO.getCategoryId());
                    newCategory.setName(createProductDTO.getCategoryName());
                    return categoryService.createCategory(newCategory);
                });

        // Convert DTO to Product
        Product product = productMapper.toEntity(createProductDTO, seller.get(), category);

        // Save Product
        Product createdProduct = productService.createProduct(product);

        // Map to ProductResponseDTO
        ProductResponseDTO responseDTO = productMapper.toResponseDTO(createdProduct);
        return ResponseEntity.ok(responseDTO);
    }

    // Get all products for a specific seller
    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(@PathVariable Long sellerId) {
        Optional<Seller> seller = sellerService.getSellerById(sellerId);
        if (!seller.isPresent()) {
            return ResponseEntity.notFound().build();  // Seller not found
        }

        List<Product> products = productService.getProductsBySeller(seller.get());
        List<ProductResponseDTO> responseDTOs = productMapper.toResponseDTOList(products);
        return ResponseEntity.ok(responseDTOs);
    }

    // Get a specific product by ID for a specific seller
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(p -> ResponseEntity.ok(productMapper.toResponseDTO(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductUpdateDTO updateDTO
    ) {
        // Ensure the product exists
        Optional<Product> existingProductOpt = productService.getProductById(id);
        if (!existingProductOpt.isPresent()) {
            return ResponseEntity.notFound().build(); // Product not found
        }

        Product existingProduct = existingProductOpt.get();

        // If category update is requested, ensure category exists or create a new one
        if (updateDTO.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(updateDTO.getCategoryId())
                    .orElseGet(() -> {
                        // Create a new category if it doesn't exist
                        Category newCategory = new Category();
                        newCategory.setId(updateDTO.getCategoryId());
                        newCategory.setName(updateDTO.getCategoryName());
                        return categoryService.createCategory(newCategory);
                    });
            existingProduct.setCategory(category);
        }

        // Update other fields
        if (updateDTO.getName() != null) existingProduct.setName(updateDTO.getName());
        if (updateDTO.getDescription() != null) existingProduct.setDescription(updateDTO.getDescription());
        if (updateDTO.getPrice() != null) existingProduct.setPrice(updateDTO.getPrice());
        if (updateDTO.getStock() != null) existingProduct.setStock(updateDTO.getStock());

        // Save the updated product
        Product updatedProduct = productService.updateProduct(existingProduct);

        // Map to ProductResponseDTO
        ProductResponseDTO responseDTO = productMapper.toResponseDTO(updatedProduct);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // Verify if the product exists
        Optional<Product> productOpt = productService.getProductById(id);
        if (!productOpt.isPresent()) {
            return ResponseEntity.notFound().build(); // Product not found
        }

        Product product = productOpt.get();

        // Check if any OrderItem references this product
        boolean isProductInOrders = orderService.hasOrdersForProduct(id);
        if (isProductInOrders) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // Cannot delete a product that is part of an order
        }

        // Delete the product
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build(); // Successfully deleted
    }


}
