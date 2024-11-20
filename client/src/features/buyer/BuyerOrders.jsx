import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './BuyerOrders.css';

const BuyerOrders = () => {
  const navigate = useNavigate();
  const [orders, setOrders] = useState([
    { id: 1, status: "Pending", totalAmount: 100, buyer: "Buyer1" },
    { id: 2, status: "Shipped", totalAmount: 150, buyer: "Buyer2" },
    { id: 3, status: "On the way", totalAmount: 80, buyer: "Buyer3" },
    { id: 4, status: "Delivered", totalAmount: 200, buyer: "Buyer4" },
    { id: 5, status: "Cancelled", totalAmount: 0, buyer: "Buyer5" },
  ]);

  // Fetch orders (currently empty, just for the useEffect)
  useEffect(() => {
    // You can fetch orders here if needed
  }, []);

  const handleCancelOrder = async (orderId, event) => {
    event.stopPropagation();  // Prevent the order click handler from being triggered
    // Logic for canceling the order
    console.log(`Cancel Order clicked for order ID: ${orderId}`);
    // Add your cancel logic here, like calling an API
  }

  const handleOrderClick = (orderId) => {
    // Redirect to order detail page
    navigate(`/buyer/order-detail/${orderId}`);
  };

  return (
    <div className="buyer-orders-container">
      <h2>Your Orders</h2>
      <ul>
        {orders.map((order) => (
          <li
            key={order.id}
            className="order-card"
            onClick={() => handleOrderClick(order.id)}  // This handles the click to view order details
          >
            <div className="order-details">
              <strong>Order ID: {order.id}</strong> - {order.status} - ${order.totalAmount}
            </div>

            {/* Cancel Order Button (only for non-Delivered and non-Cancelled orders) */}
            {order.status !== "Delivered" && order.status !== "Cancelled" && (
              <button onClick={(e) => handleCancelOrder(order.id, e)}>Cancel Order</button>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default BuyerOrders;
