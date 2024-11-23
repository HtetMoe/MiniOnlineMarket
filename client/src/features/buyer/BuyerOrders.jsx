import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './BuyerOrders.css';
import buyerService from '../../services/buyerService.jsx';
import axios from 'axios';

const BuyerOrders = () => {
  const navigate = useNavigate();
  const [orders, setOrders] = useState([
    { id: 1, status: "Pending", totalAmount: 100, buyer: "Buyer1" },
    { id: 2, status: "Shipped", totalAmount: 150, buyer: "Buyer2" },
    { id: 3, status: "On the way", totalAmount: 80, buyer: "Buyer3" },
    { id: 4, status: "Delivered", totalAmount: 200, buyer: "Buyer4" },
    { id: 5, status: "Cancelled", totalAmount: 0, buyer: "Buyer5" },
  ]);


  useEffect(() => {
    // const fetchBuyerOrders = async () => {
    //   try {
    //     const buyerId = 1;
    //     const data = await buyerService.getBuyerOrders(buyerId);
    //     setOrders(data); 
    //   } catch (error) {
    //     console.error('Error fetching orders:', error);
    //   }
    // };
    // fetchBuyerOrders();
  }, []);

  const handleCancelOrder = async (orderId, event) => {
    // event.stopPropagation();  // Prevent the order click handler from being triggered
    // try {
    //   await buyerService.cancelOrder(orderId); 
    //   setOrders(orders.filter(order => order.id !== orderId));  
    // } catch (error) {
    //   console.error('Error cancelling order:', error);
    // }
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
