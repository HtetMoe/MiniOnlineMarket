package com.momarket.product.category;

import com.momarket.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "categories")  // Table name in the database
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate unique ID
    private Long categoryId;

    @Column(name = "name", nullable = false, length = 100)  // Column for category name
    private String name;

    @Column(name = "description", length = 255)  // Column for category description
    private String description;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    // One-to-many relationship between Category and Product
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

}
