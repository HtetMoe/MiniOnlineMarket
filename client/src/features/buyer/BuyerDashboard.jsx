import React from 'react';
import { Link, Outlet } from 'react-router-dom';
import './BuyerDashboard.css';

const BuyerDashboard = () => {
  return (
    <div className="buyer-dashboard-container">
      <header className="buyer-dashboard-header">
        <h3>Welcome to Your Buyer Dashboard</h3>
      </header>

      <nav className="buyer-dashboard-nav">
        <ul>
          <li><Link to="/buyer/products">Products</Link></li>
          <li><Link to="/buyer/cart">Shopping Cart</Link></li>
          <li><Link to="/buyer/orders">Order History</Link></li>
          <li><Link to="/buyer/reviews">View Reviews</Link></li>
        </ul>
      </nav>

      <div className="outlet-content">
        <Outlet />
      </div>
    </div>
  );
};

export default BuyerDashboard;
