package com.momarket.order;
import com.momarket.product.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Foreign key to Order

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Foreign key to Product

    @Column(name = "quantity", nullable = false)
    private Integer quantity; // Quantity of the product in the order

    @Column(name = "price", nullable = false)
    private Double price; // Price per unit of the product

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;
}