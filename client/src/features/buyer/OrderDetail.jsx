import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './OrderDetail.css';
import { jsPDF } from 'jspdf';
import buyerService from '../../services/buyerService.jsx';
import axios from 'axios';

const OrderDetail = () => {
  const { orderId } = useParams(); // Get the orderId from the URL
  const navigate = useNavigate();

  // Sample order data (replace this with real fetch later)
  const [order, setOrder] = useState({
    id: 1,
    status: "Delivered",
    totalAmount: 250,
    buyer: "Buyer1",
    items: [
      { id: 1, productName: "Product A", quantity: 2, price: 50 },
      { id: 2, productName: "Product B", quantity: 1, price: 75 },
      { id: 3, productName: "Product C", quantity: 3, price: 30 },
    ],
  });

  useEffect(() => {
    // Fetch order details
    // const fetchOrderDetails = async (orderId) => {
    //   try {
    //     const data = await buyerService.getOrderDetail(orderId);
    //     setOrder(data);
    //   } catch (error) {
    //     console.error('Error fetching order details:', error);
    //   }
    // };
    // fetchOrderDetails(orderId);
  }, [orderId]);

  // Handle PDF download
  const handleDownloadPDF = () => {
    const doc = new jsPDF();
    doc.text('Order Receipt', 20, 20);
    doc.text(`Order ID: ${order.id}`, 20, 30);
    doc.text(`Buyer: ${order.buyer}`, 20, 60);
    doc.text(`Status: ${order.status}`, 20, 50);
    doc.text(`Total Amount: $${order.totalAmount}`, 20, 40);
    doc.save(`order_${order.id}_receipt.pdf`);
  };

  // Handle redirect to the ReviewSubmission page with productId
  const handleReviewClick = (productId) => {
    navigate(`/buyer/review/${productId}`);
  };

  const handleBackClick = () => {
    navigate(-1);  // Go back to the previous page
  };

  if (!order) {
    return <div>Loading...</div>;
  }

  return (
    <div className="order-detail-container">
      <button className="back-button" onClick={handleBackClick}>← Back</button>

      <h2>Order Detail for Order #{order.id}</h2>
      <div>
        <strong>Order ID:</strong> {order.id}
        <br />
        <strong>Status:</strong> {order.status}
        <br />
        <strong>Buyer:</strong> {order.buyer}
      </div>

      <div className="order-items">
        <h3>Items in this Order</h3>
        <ul>
          {order.items.map((item) => (
            <li key={item.id}>
              <strong>{item.productName}</strong> {item.quantity} x ${item.price}
              <button onClick={() => handleReviewClick(item.id)} className="review-button">
                Write a Review
              </button>
            </li>
          ))}
        </ul>
      </div>

      {/* Total Amount at the bottom right corner */}
      <div className="total-amount">
        <strong>Total Amount: </strong>${order.totalAmount}
      </div>

      <div className="receipt-actions">
        <button onClick={handleDownloadPDF}>Download Receipt (PDF)</button>
      </div>
    </div>
  );
};

export default OrderDetail;
