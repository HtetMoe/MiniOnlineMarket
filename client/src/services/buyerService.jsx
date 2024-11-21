import axios from 'axios';

// Base URL for your API
const BASE_URL = 'https://localhost:8080/api/v1/buyers';  // Replace with your actual API URL

// Get products for the buyer
const getBuyerProducts = () => {
  return axios.get(`${BASE_URL}/products`);
};

const getProductDetail = async (productId) => {
  const response = await axios.get(`/api/buyers/product/${productId}`);
  return response.data;
};


// Add item to cart
const addItemToCart = (productId, quantity) => {
  return axios.post(`${BASE_URL}/cart`, { productId, quantity });
};

// Get shopping cart items for a buyer
const getShoppingCart = () => {
  return axios.get(`${BASE_URL}/cart`);
};

// Remove item from the shopping cart
const removeCartItem = (itemId) => {
  return axios.delete(`${BASE_URL}/cart/${itemId}`);
};

// Place an order
const placeOrder = (cart) => {
  return axios.post(`${BASE_URL}/orders`, { cart });
};

// Get buyer's order history
const getBuyerOrders = () => {
  return axios.get(`${BASE_URL}/orders`);
};

// Cancel an order
const cancelOrder = (orderId) => {
  return axios.put(`${BASE_URL}/orders/${orderId}/cancel`);
};

// Get reviews for a product
const getReviews = (productId) => {
  return axios.get(`${BASE_URL}/reviews/${productId}`);
};

const submitReview = (orderId, rating, comment) => {
  return axios.post(`${BASE_URL}/reviews`, { orderId, rating, comment });
};

// Exporting all the functions
const buyerService = {
  getBuyerProducts,
  getProductDetail,
  addItemToCart,
  getShoppingCart,
  removeCartItem,
  placeOrder,
  getBuyerOrders,
  cancelOrder,
  getReviews,
  submitReview
};

export default buyerService;
