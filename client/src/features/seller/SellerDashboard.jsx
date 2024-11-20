import React from 'react'
import { useState, useEffect } from 'react';
import { Link, Outlet } from 'react-router-dom';

const SellerDashboard = () => {
  const [productCount, setProductCount] = useState(0);
  const [orderCount, setOrderCount] = useState(0);
  const [stockCount, setStockCount] = useState(0);

  //fetch data
  useEffect(() => {

  }, []);

  return (
    <div className="seller-dashboard-container">
      {/* Header Section */}
      <h2>Seller Dashboard</h2>

      {/* Seller Statistics */}
      <div className="stats-container">
        <div className="stat-card">
          <h3>Products</h3>
          <p>{productCount}</p>
        </div>
        <div className="stat-card">
          <h3>Orders</h3>
          <p>{orderCount}</p>
        </div>
        <div className="stat-card">
          <h3>Stock</h3>
          <p>{stockCount}</p>
        </div>
      </div>

      {/* Navigation Links */}
      <nav>
        <ul>
          <li><Link to="/seller/manage-products">Manage Products</Link></li>
          <li><Link to="/seller/manage-orders">Manage Orders</Link></li>
        </ul>
      </nav>

      {/* Outlet for Content */}
      <div className="outlet-content">
        <Outlet />
      </div>
    </div>
  )
}

export default SellerDashboard