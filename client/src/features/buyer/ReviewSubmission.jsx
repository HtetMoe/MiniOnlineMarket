import React from 'react'
import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import './ReviewSubmission.css'

const ReviewSubmission = () => {
  const [review, setReview] = useState({ rating: 0, comment: "" });
  const { orderId } = useParams();
  const [hoverRating, setHoverRating] = useState(0);
  const stars = [1, 2, 3, 4, 5];

  //fetch data
  useEffect(() => {
  }, [orderId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setReview({ ...review, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

  };

  return (
    <div className="review-submission-container">
      <h2>Submit Review for Order #{orderId}</h2>
      <form onSubmit={handleSubmit}>
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

        <button type="submit">Submit Review</button>
      </form>
    </div>
  )
}

export default ReviewSubmission