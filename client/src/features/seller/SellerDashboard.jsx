import React from 'react'
import { useState, useEffect } from 'react';
import { Link, Outlet } from 'react-router-dom';

const SellerDashboard = () => {

  //fetch data
  useEffect(() => {

  }, []);

  return (
    <div className="seller-dashboard-container">
      {/* Header Section */}
      <h2>Seller Dashboard</h2>

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