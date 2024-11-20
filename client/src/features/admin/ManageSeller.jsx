import React from 'react'
import { useState, useEffect } from 'react';
import './ManageSellers.css'

const ManageSeller = () => {
    const [sellers, setSellers] = useState([
        { id: 111, email: "sara@gmail.com", name: 'Sara', status: 'Approved' },
        { id: 111, email: "dann@gmail.com", name: 'Dann', status: 'Pending' }
    ]);

    //fetch sellers
    useEffect(() => {

    }, []);

    const handleApprove = (id) => {

    };

    const handleReject = (id) => {
    };

    return (
        <div>
            <h2>Manage Sellers</h2>
            <table>
                <thead>
                    <tr>
                        <th>Seller Name</th>
                        <th>Email</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {sellers.map((seller) => (
                        <tr key={seller.id}>
                            <td>{seller.name}</td>
                            <td>{seller.email}</td>
                            <td>{seller.status}</td>
                            <td>
                                {seller.status === 'Pending' && (
                                    <>
                                        <button onClick={() => handleApprove(seller.id)}>Approve</button>
                                        <button onClick={() => handleReject(seller.id)}>Reject</button>
                                    </>
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default ManageSeller