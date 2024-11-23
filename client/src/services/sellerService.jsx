import axios from 'axios';

const API_URL = 'http://localhost:8005/api/v1';

// Fetch products for a seller
const getSellerProducts = (sellerID) => {
  console.log('sellerID: ', sellerID)
  return axios.get(`${API_URL}/products/seller/${sellerID}`);
};

// Create a new product for the seller
const createProduct = (productData, sellerID) => {
  console.log('productData : ', productData, ', sellerID : ', sellerID)
  return axios.post(`${API_URL}/products/seller/${sellerID}`, productData);
};

// Update an existing product for the seller
const updateProduct = (productId, productData) => {
  return axios.put(`${API_URL}/products/${productId}`, productData);
};

// Delete a product for the seller
const deleteProduct = (productId) => {
  return axios.delete(`${API_URL}/products/${productId}`);
};

// Fetch orders for a seller
const getSellerOrders = () => {
  return axios.get('/api/sellers/orders');
};

// Update the status of an order
const updateOrderStatus = (orderId, newStatus) => {
  return axios.put(`/api/sellers/orders/${orderId}/status`, { status: newStatus });
};

// Delete an order for the seller
const deleteOrder = (orderId) => {
  return axios.delete(`/api/sellers/orders/${orderId}`);
};

// Exporting all the functions to be used in the components
const sellerService = {
  getSellerProducts,
  createProduct,
  updateProduct,
  deleteProduct,
  getSellerOrders,
  updateOrderStatus,
  deleteOrder,
};

export default sellerService;
