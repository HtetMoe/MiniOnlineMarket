import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './ProductDetail.css';

const ProductDetail = () => {
  const { id } = useParams(); // Get the product id from the URL
  const navigate = useNavigate();

  // Sample data for the product (normally fetched from an API)
  const [product] = useState({
    id: 1,
    name: 'Product A',
    category: 'Electronics',
    price: 100,
    description: 'An electronic item',
    imageUrl: 'https://via.placeholder.com/300', // Adjusted for 300px
    reviews: [
      { id: 1, review: 'Great product!', rating: 5 },
      { id: 2, review: 'Good value for money', rating: 4 }
    ]
  });

  const [cart, setCart] = useState([]);
  const [quantity, setQuantity] = useState(1);

  const handleAddToCart = () => {
    const productWithQuantity = { ...product, quantity };
    setCart([...cart, productWithQuantity]);
    alert(`${product.name} added to cart with quantity ${quantity}`);

    // Redirect back to the product list
    navigate('/buyer/products');
  };

  const handleQuantityChange = (e) => {
    const value = Math.max(1, Math.min(10, e.target.value)); // Ensure quantity is between 1 and 10
    setQuantity(value);
  };

  return (
    <div className="product-detail-container">
      <div className="product-detail-body">
        <div className="product-image">
          <img src={product.imageUrl} alt={product.name} />
        </div>

        <div className="product-description">
          <h2>{product.name}</h2>
          <p>{product.category}</p>
          <p>{product.description}</p>
          <p><strong>Price:</strong> ${product.price}</p>

          {/* Quantity Selector */}
          <div className="quantity-selector">
            <label>Quantity: </label>
            <input
              type="number"
              value={quantity}
              onChange={handleQuantityChange}
              min="1"
              max="10"
            />
          </div>

          <button onClick={handleAddToCart}>Add to Cart</button>
        </div>

        <div className="product-reviews">
          <h3>Reviews:</h3>
          <ul>
            {product.reviews.map((review) => (
              <li key={review.id}>
                <p>{review.review}</p>
                <p>Rating: {review.rating} stars</p>
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default ProductDetail;
