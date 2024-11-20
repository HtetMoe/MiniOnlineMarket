import React from 'react'
import { Link, Outlet } from 'react-router-dom'
import './AdminDashboard.css'
import { useState, useEffect } from 'react'

const AdminDashboard = () => {
  return (
    <div className="admin-dashboard-container">
      {/* Header Section */}
      <h2>Admin Dashboard</h2>

      {/* Navigation Links */}
      <nav>
        <ul>
          <li><Link to="/admin/manage-sellers">Manage Sellers</Link></li>
          <li><Link to="/admin/manage-reviews">Manage Reviews</Link></li>
        </ul>
      </nav>

      {/* Outlet for Content */}
      <div className="outlet-content">
        <Outlet />
      </div>
    </div>
  )
}

export default AdminDashboard