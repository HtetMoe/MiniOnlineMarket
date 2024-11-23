package com.momarket.product;

import com.momarket.product.category.Category;
import com.momarket.product.category.CategoryService;
import com.momarket.users.seller.Seller;
import com.momarket.users.seller.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products/")
public class ProductController {

    private final ProductService productService;
    private final SellerService sellerService;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    // Create a new product for a seller
    @PostMapping("seller/{sellerId}")
    public ResponseEntity<ProductDTO> createProduct(@PathVariable Long sellerId, @RequestBody CreateProductDTO createProductDTO) {
        Optional<Seller> seller = sellerService.getSellerById(sellerId);

        if (seller.isPresent()) {
            // Get the seller instance
            Seller sellerEntity = seller.get();

            // Find the category using the categoryId from the request
            Optional<Category> category = categoryService.getCategoryById(createProductDTO.getCategoryId());

            if (!category.isPresent()) {
                // If the category does not exist, create it
                Category newCategory = new Category();
                newCategory.setName(createProductDTO.getCategoryName()); // Assuming the category name comes from the request
                newCategory.setCreatedAt(LocalDateTime.now());  // Set the creation timestamp
                newCategory.setUpdatedAt(LocalDateTime.now());  // Set the update timestamp
                categoryService.createCategory(newCategory);  // Save the new category

                category = Optional.of(newCategory);  // Update the category variable to hold the newly created category
            }

            // Create the new product and associate it with the seller and category
            Product product = new Product();
            product.setName(createProductDTO.getName());
            product.setDescription(createProductDTO.getDescription());
            product.setPrice(createProductDTO.getPrice());
            product.setStockQuantity(createProductDTO.getStockQuantity());
            product.setSeller(sellerEntity);
            product.setCategory(category.get());  // Set the category of the product

            // Save the product using the product service
            Product createdProduct = productService.createProduct(product);

            // Map the created product to ProductDTO
            ProductDTO productDTO = productMapper.from(createdProduct);

            return ResponseEntity.ok(productDTO);  // Return the created product
        } else {
            return ResponseEntity.notFound().build();  // Return 404 if seller not found
        }
    }


    // Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    // Get all products of a seller
    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<ProductDTO>> getProductsBySeller(@PathVariable Long sellerId) {
        List<Product> products = productService.getProductsBySeller(sellerId);
        return ResponseEntity.ok(products.stream().map(productMapper::from).toList());
    }

    // Update an existing product
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        // Ensure we're updating the correct product by setting the productId
        product.setId(productId);

        // Update the product using the product service
        Product updatedProduct = productService.updateProduct(product);

        // Map the updated product to ProductDTO
        ProductDTO productDTO = productMapper.from(updatedProduct);

        // Return the updated product as a ProductDTO
        return ResponseEntity.ok(productDTO);
    }


    // Get product details by ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    // Delete a product
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
