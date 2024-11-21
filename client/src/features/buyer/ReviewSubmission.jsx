import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './ReviewSubmission.css';
import buyerService from '../../services/buyerService'; // Import buyerService to call APIs

const ReviewSubmission = () => {
  const { orderId } = useParams(); // Get the orderId from the URL
  const navigate = useNavigate();

  const [review, setReview] = useState({ rating: 0, comment: "" });
  const [hoverRating, setHoverRating] = useState(0);
  const stars = [1, 2, 3, 4, 5];

  // Handle rating and comment change
  const handleChange = (e) => {
    const { name, value } = e.target;
    setReview({ ...review, [name]: value });
  };

  // Handle submitting the review
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      if (!review.rating || !review.comment) {
        alert("Please provide both a rating and a comment.");
        return;
      }
      const response = await buyerService.submitReview(orderId, review.rating, review.comment);
      if (response) {
        alert('Review submitted successfully!');
        // navigate(`/buyer/order-detail/${orderId}`);  // Uncomment if needed
      }
    } catch (error) {
      console.error('Error submitting review:', error);
      alert('Failed to submit review. Please try again later.');
    }
  };

  return (
    <div className="review-submission-container">
      <h2>Submit Review for Order #{orderId}</h2>
      <form onSubmit={handleSubmit}>
        {/* Rating Input (Star Rating) */}
        <div className="rating-container">
          <label>Rating:</label>
          <div className="stars">
            {stars.map((star) => (
              <button
                type="button"
                key={star}
                className={`star-button ${review.rating >= star || hoverRating >= star ? "filled" : ""}`}
                onClick={() => setReview({ ...review, rating: star })}
                onMouseEnter={() => setHoverRating(star)}
                onMouseLeave={() => setHoverRating(0)}
              >
                ★
              </button>
            ))}
          </div>
        </div>

        {/* Comment Input (Textarea) */}
        <div>
          <label>Comment:</label>
          <textarea
            name="comment"
            value={review.comment}
            onChange={handleChange}
            placeholder="Write your review here..."
            required
          />
        </div>

        {/* Submit Button */}
        <button type="submit">Submit Review</button>
      </form>
    </div>
  );
};

export default ReviewSubmission;
