import React from 'react'
import { useState, useEffect } from 'react'
import './ProductReviews.css'
import buyerService from '../../services/buyerService.jsx';
import axios from 'axios';

const Reviews = () => {
  const [reviews, setReviews] = useState([
    { id: 1, productName: "Product A", rating: 5, comment: "Excellent product! Highly recommend." },
    { id: 2, productName: "Product B", rating: 4, comment: "Good quality, but a bit pricey." },
    { id: 3, productName: "Product C", rating: 3, comment: "Average product, meets expectations." },
    { id: 4, productName: "Product D", rating: 2, comment: "Not satisfied with the performance." },
    { id: 5, productName: "Product E", rating: 5, comment: "Amazing! Will definitely buy again." }
  ]);

  //fetch reviews
  useEffect(() => {
  }, []);

  return (
    <div className="reviews-container">
      <h2>Product Reviews</h2>
      <ul>
        {reviews.map((review) => (
          <li key={review.id}>
            <div className="review-details">
              <strong>Product Name:</strong> {review.productName} <br />
              <strong>Rating:</strong> {review.rating} <br />
              <strong>Review:</strong> {review.comment}
            </div>
          </li>
        ))}
      </ul>
    </div>
  )
}

export default Reviews