import React from 'react'
import { useState, useEffect } from 'react'
import './ManageOrders.css'

const ManageOrders = () => {
  const [orders, setOrders] = useState([
    { id: 1, status: "Pending", totalAmount: 100, buyer: "Buyer1" },
    { id: 2, status: "Shipped", totalAmount: 150, buyer: "Buyer2" },
    { id: 3, status: "On the way", totalAmount: 80, buyer: "Buyer3" },
    { id: 4, status: "Delivered", totalAmount: 200, buyer: "Buyer4" },
    { id: 5, status: "Cancelled", totalAmount: 0, buyer: "Buyer5" }
  ]);

  //fetch orders
  useEffect(() => {
  }, []);

  const handleUpdateOrderStatus = async (orderId, newStatus) => {
  };

  const handleCancelOrder = async (orderId) => {
  };

  const handleDeleteOrder = async (orderId) => {
  }

  return (
    <div className="order-management-container">
      <h2>Order Management</h2>

      {/* List of Orders */}
      <div>
        <h3>Existing Orders</h3>
        <ul>
          {orders.map((order) => (
            <li key={order.id} className="order-card">
              <div className="order-details">
                <strong>Order ID: {order.id}</strong> -
                {order.status} - ${order.totalAmount} -
                <strong>Buyer:</strong> {order.buyer}
              </div>

              {/* Manage Order Status */}
              <div className="button-container">
                {order.status === "Pending" && (
                  <button onClick={() => handleUpdateOrderStatus(order.id, 'Shipped')}>Mark as Shipped</button>
                )}
                {order.status === "Shipped" && (
                  <button onClick={() => handleUpdateOrderStatus(order.id, 'On the way')}>Mark as On the way</button>
                )}
                {order.status === "On the way" && (
                  <button onClick={() => handleUpdateOrderStatus(order.id, 'Delivered')}>Mark as Delivered</button>
                )}
                {order.status !== 'Cancelled' && (
                  <button onClick={() => handleCancelOrder(order.id)}>Cancel Order</button>
                )}

                <button className="delete" onClick={() => handleDeleteOrder(order.id)}>Delete Order</button>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  )
}

export default ManageOrders