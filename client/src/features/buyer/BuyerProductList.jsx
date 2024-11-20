import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './BuyerProductList.css';

const BuyerProductList = () => {
  const navigate = useNavigate();

  // Sample product data
  const [products, setProducts] = useState([
    { id: 1, name: 'Product A', category: 'Electronics', price: 100, description: 'An electronic item', imageUrl: 'https://via.placeholder.com/150' },
    { id: 2, name: 'Product B', category: 'Clothing', price: 50, description: 'A nice shirt', imageUrl: 'https://via.placeholder.com/150' },
    { id: 3, name: 'Product C', category: 'Electronics', price: 200, description: 'A high-end gadget', imageUrl: 'https://via.placeholder.com/150' },
    { id: 4, name: 'Product D', category: 'Furniture', price: 150, description: 'A wooden chair', imageUrl: 'https://via.placeholder.com/150' },
    { id: 5, name: 'Product E', category: 'Clothing', price: 30, description: 'A cool t-shirt', imageUrl: 'https://via.placeholder.com/150' }
  ]);

  // Cart state
  const [cart, setCart] = useState([]);

  // Handle adding to cart
  const handleAddToCart = (productId, quantity, event) => {
    event.stopPropagation();  // Stop the event from propagating to the parent click handler
    
    const product = products.find(p => p.id === productId);
    if (product) {
      const productWithQuantity = { ...product, quantity }; // Add quantity to product
      setCart([...cart, productWithQuantity]); // Add the product to the cart
      alert(`${product.name} added to cart with quantity ${quantity}`);
    }
  };

  // Navigate to Product Detail
  const handleProductClick = (productId) => {
    navigate(`/buyer/product/${productId}`);
  };

  // Price filter logic
  const [priceFilter, setPriceFilter] = useState({ from: 0, to: 500 });
  const filteredProducts = products.filter(product => product.price >= priceFilter.from && product.price <= priceFilter.to);

  return (
    <div className="buyer-product-list-container">
      {/* Cart Button */}
      <div className="cart-link">
        <button className="cart-button" onClick={() => navigate('/buyer/cart')}>
          <i className="fas fa-shopping-cart"></i>
          <span className="cart-item-count">{cart.length}</span>
          Go to Cart
        </button>
      </div>

      {/* Price Range Filter */}
      <div className="filters">
        <label>Price Range:
          <div className="price-range">
            <input
              type="number"
              min="0"
              max="500"
              value={priceFilter.from}
              onChange={(e) => setPriceFilter({ ...priceFilter, from: Math.max(0, +e.target.value) })}
              placeholder="From"
            />
            <span>to</span>
            <input
              type="number"
              min="0"
              max="500"
              value={priceFilter.to}
              onChange={(e) => setPriceFilter({ ...priceFilter, to: Math.min(500, +e.target.value) })}
              placeholder="To"
            />
          </div>
        </label>
      </div>

      {/* Product List */}
      <div className="product-grid">
        {filteredProducts.map((product) => (
          <div key={product.id} className="product-card" onClick={() => handleProductClick(product.id)}>
            <img src={product.imageUrl} alt={product.name} className="product-image" />
            <div className="product-info">
              <h3>{product.name}</h3>
              <p>{product.description}</p>
              <p><strong>Price:</strong> ${product.price}</p>
              <p><strong>Category:</strong> {product.category}</p>
              <div className="quantity">
                <label>Quantity: </label>
                <input type="number" defaultValue="1" min="1" max="10" id={`quantity-${product.id}`} />
              </div>
            </div>
            {/* Ensure that clicking the button only triggers the add to cart event */}
            <button onClick={(e) => handleAddToCart(product.id, document.getElementById(`quantity-${product.id}`).value, e)}>
              Add to Cart
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default BuyerProductList;
