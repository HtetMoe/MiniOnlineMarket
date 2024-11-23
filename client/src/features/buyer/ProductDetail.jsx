import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './ProductDetail.css';
import axios from 'axios';
import buyerService from '../../services/buyerService.jsx';

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

  //fetch product detail
  useEffect(() => {
    // const fetchProductDetails = async (productId) => {
    //   try {
    //     const data = await buyerService.getProductDetail(productId);  
    //     setProduct(data); 
    //   } catch (error) {
    //     console.error('Error fetching product details:', error);
    //   }
    // };
    // fetchProductDetails(id);
  }, [id]);


  const handleAddToCart = async() => {
    // if (product) {
    //   const productWithQuantity = { ...product, quantity };

    //   try {
    //     // Add product to cart using buyerService API
    //     await buyerService.addItemToCart(product.id, quantity);
    //     alert(`${product.name} added to cart with quantity ${quantity}`);
    //     // Redirect to product list or cart after adding
    //     navigate('/buyer/cart');
    //   } catch (error) {
    //     console.error('Error adding product to cart:', error);
    //     alert('Failed to add product to cart.');
    //   }
    // }
  };

  const handleQuantityChange = (e) => {
    const value = Math.max(1, Math.min(10, e.target.value)); // Ensure quantity is between 1 and 10
    setQuantity(value);
  };

  const handleBack = () => {
    navigate(-1); // Navigate to the previous page
  };

  return (
    <div className="product-detail-container">
     
      <div className="product-detail-body">
        {/* Back Button */}
        <button className="back-button" onClick={handleBack}>
          ← Back
        </button>

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
