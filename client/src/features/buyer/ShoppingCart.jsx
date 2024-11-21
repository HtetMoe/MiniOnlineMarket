import React from 'react'
import './ShoppingCart.css'
import { useState, useEffect } from 'react';
import buyerService from '../../services/buyerService.jsx';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const ShoppingCart = () => {
    const navigate = useNavigate();

    const [cartItems, setCartItems] = useState([
        { id: 1, productName: "Product A", quantity: 2, price: 50, totalAmount: 100 },
        { id: 2, productName: "Product B", quantity: 1, price: 75, totalAmount: 75 },
        { id: 3, productName: "Product C", quantity: 3, price: 30, totalAmount: 90 },
        { id: 4, productName: "Product D", quantity: 1, price: 120, totalAmount: 120 }
    ]);

    //fetch cart item
    useEffect(() => {
        // const fetchCartItems = async () {
        //     try {
        //         const data = await buyerService.getShoppingCart();  // Call the API to get cart items
        //         setCartItems(data);
        //     } catch (error) {
        //         console.error("Error fetching cart items:", error);
        //     }
        // }
        // fetchCartItems();
    }, []);

    const handleRemoveItem = async (itemId) => {
        try {
            await buyerService.removeCartItem(itemId);  // Call API to remove item
            setCartItems(cartItems.filter(item => item.id !== itemId));  // Remove item from local state
        } catch (error) {
            console.error("Error removing item:", error);
        }
    }

    const handlePlaceOrder = async () => {
        navigate(`/buyer/order-detail/${1}`);
        // try {
        //     const order = await buyerService.placeOrder(cartItems);  // Send cart items for order placement
        //     alert("Order placed successfully!");
        //     setCartItems([]);  // Clear the cart after placing the order
        //     navigate(`/buyer/order-detail/${order.id}`);
        // } catch (error) {
        //     console.error("Error placing order:", error);
        //     alert("Failed to place order.");
        // }
    }

    return (
        <div className="shopping-cart-container">
            <h2>Your Shopping Cart</h2>
            <ul>
                {cartItems.map((item) => (
                    <li key={item.id}>
                        <div className="cart-item-details">
                            <strong>{item.productName}</strong>
                            <div className="item-price">
                                ${item.price} x {item.quantity} = ${item.totalAmount}
                            </div>
                        </div>
                        <div className="cart-item-actions">
                            <button className="remove-item" onClick={() => handleRemoveItem(item.id)}>
                                Remove
                            </button>
                        </div>
                    </li>
                ))}
            </ul>
            <button className="place-order" onClick={handlePlaceOrder}>
                Place Order
            </button>
        </div>
    )
}

export default ShoppingCart