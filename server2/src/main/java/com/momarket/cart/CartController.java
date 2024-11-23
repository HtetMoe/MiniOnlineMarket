package com.momarket.cart;

import com.momarket.product.Product;
import com.momarket.product.ProductRepository;
import com.momarket.user.buyer.Buyer;
import com.momarket.user.buyer.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/buyers")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    // Get Cart for a specific buyer
    @GetMapping("/{id}/cart")
    public ResponseEntity<CartDTO> getCartByBuyerId(@PathVariable Long id) {
        CartDTO cart = cartService.getCartByBuyerId(id);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    // POST: Add item to the cart
    @PostMapping("/{buyerId}/cart/items")
    public ResponseEntity<CartItemDTO> addItemToCart(@PathVariable Long buyerId, @RequestBody CartItemDTO cartItemDTO) {
        // Find the buyer by ID
        Optional<Buyer> buyerOptional = buyerRepository.findById(buyerId);
        if (!buyerOptional.isPresent()) {
            return ResponseEntity.notFound().build(); // If buyer not found, return 404
        }

        Buyer buyer = buyerOptional.get();
        Cart cart = buyer.getCart(); // Get the buyer's cart

        // Fetch the Product entity using the productId from CartItemDTO
        Optional<Product> productOptional = productRepository.findById(cartItemDTO.getProductId());
        if (!productOptional.isPresent()) {
            return ResponseEntity.notFound().build(); // If product not found, return 404
        }

        Product product = productOptional.get(); // Get the Product entity

        // Convert CartItemDTO to CartItem entity and associate with the cart and product
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(cartItemDTO.getQuantity())
                .subtotal(product.getPrice().multiply(BigDecimal.valueOf(cartItemDTO.getQuantity()))) // Calculate the subtotal
                .build();

        // Save the item to the cart
        cartItemRepository.save(cartItem);

        // Return the saved item as a response
        return ResponseEntity.ok(cartItemDTO);
    }


    // PUT: Update item quantity in the cart
    @PutMapping("/{buyerId}/cart/items/{itemId}")
    public ResponseEntity<CartItemDTO> updateCartItem(@PathVariable Long buyerId, @PathVariable Long itemId, @RequestBody CartItemDTO cartItemDTO) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(itemId);
        if (!cartItemOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        CartItem cartItem = cartItemOptional.get();
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setSubtotal(cartItemDTO.getPrice()); // Update price if necessary

        // Save the updated cart item
        cartItemRepository.save(cartItem);

        return ResponseEntity.ok(cartItemDTO);
    }

    // DELETE: Remove item from the cart
    @DeleteMapping("/{buyerId}/cart/items/{itemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long buyerId, @PathVariable Long itemId) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(itemId);
        if (!cartItemOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        cartItemRepository.deleteById(itemId);

        return ResponseEntity.noContent().build();
    }

    // DELETE: Clear all items from the cart
    @DeleteMapping("/{buyerId}/cart")
    public ResponseEntity<Void> clearCart(@PathVariable Long buyerId) {
        Optional<Buyer> buyerOptional = buyerRepository.findById(buyerId);
        if (!buyerOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Buyer buyer = buyerOptional.get();
        Cart cart = buyer.getCart();

        // Delete all items in the cart
        cartItemRepository.deleteAll(cart.getCartItems());

        return ResponseEntity.noContent().build();
    }

}
