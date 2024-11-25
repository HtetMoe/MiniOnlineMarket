import React from 'react'
import { useState, useEffect } from 'react';
import './ManageReviews.css'
import adminService from '../../services/adminService.jsx';

const ManageReviews = () => {
    const [reviews, setReviews] = useState([
        { id: 111, productName: 'Tshirt', reviewerName: 'Moe', rating: 3, comment: 'The design looks luxirious.' }
    ]);

    //fetch reviews
    useEffect(() => {
        // adminService.getReviews().then((response) => {
        //     setReviews(response.data);
        // });
    }, []);

    const handleDeleteReview = (id) => {
        adminService.deleteReview(id).then(() => {
            setReviews(reviews.filter((review) => review.id !== id));
        });
    };

    return (
        <div>
            <h2>Manage Reviews</h2>
            <table>
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Reviewer</th>
                        <th>Rating</th>
                        <th>Comment</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {reviews.map((review) => (
                        <tr key={review.id}>
                            <td>{review.productName}</td>
                            <td>{review.reviewerName}</td>
                            <td>{review.rating}</td>
                            <td>{review.comment}</td>
                            <td>
                                <button onClick={() => handleDeleteReview(review.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default ManageReviews