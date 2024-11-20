import React from 'react'
import './ShoppingCart.css'
import { useState, useEffect } from 'react';

const ShoppingCart = () => {
    const [cartItems, setCartItems] = useState([
        { id: 1, productName: "Product A", quantity: 2, price: 50, totalAmount: 100 },
        { id: 2, productName: "Product B", quantity: 1, price: 75, totalAmount: 75 },
        { id: 3, productName: "Product C", quantity: 3, price: 30, totalAmount: 90 },
        { id: 4, productName: "Product D", quantity: 1, price: 120, totalAmount: 120 }
    ]);


    useEffect(() => {
    }, []);

    const handleRemoveItem = async (itemId) => {
    }

    const handlePlaceOrder = async () => {
    }

    return (
        <div className="shopping-cart-container">
            <h2>Your Shopping Cart</h2>
            <ul>
                {cartItems.map((item) => (
                    <li key={item.id}>
                        <div className="cart-item-details">
                            <strong>{item.productName}</strong> ${item.price} x {item.quantity}
                        </div>
                        <div className="cart-item-actions">
                            <button onClick={() => handleRemoveItem(item.id)}>Remove</button>
                        </div>
                    </li>
                ))}
            </ul>
            <button onClick={handlePlaceOrder}>Place Order</button>
        </div>
    )
}

export default ShoppingCart