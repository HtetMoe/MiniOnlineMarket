import axios from 'axios';

// Fetch products for a seller
const getSellerProducts = () => {
  return axios.get('/api/sellers/products');
};

// Create a new product for the seller
const createProduct = (productData) => {
  return axios.post('/api/sellers/products', productData);
};

// Update an existing product for the seller
const updateProduct = (productId, productData) => {
  return axios.put(`/api/sellers/products/${productId}`, productData);
};

// Delete a product for the seller
const deleteProduct = (productId) => {
  return axios.delete(`/api/sellers/products/${productId}`);
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
