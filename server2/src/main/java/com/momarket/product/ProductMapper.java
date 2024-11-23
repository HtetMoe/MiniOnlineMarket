package com.momarket.product;

import com.momarket.product.category.Category;
import com.momarket.user.seller.Seller;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    // Convert CreateProductDTO to Product entity
    public Product toEntity(CreateProductDTO createProductDTO, Seller seller, Category category) {
        return Product.builder()
                .name(createProductDTO.getName())
                .description(createProductDTO.getDescription())
                .price(createProductDTO.getPrice())
                .stock(createProductDTO.getStock())
                .seller(seller)  // Set the seller
                .category(category)  // Set the category
                .build();
    }

    // Convert Product entity to ProductResponseDTO
    public ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getSeller().getUser().getName()
        );
    }

    // Convert list of Product entities to list of ProductResponseDTOs
    public List<ProductResponseDTO> toResponseDTOList(List<Product> products) {
        return products.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
