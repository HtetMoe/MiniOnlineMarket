import axios from 'axios';

// Fetch all sellers
const getSellers = () => {
  return axios.get('/api/admin/sellers');
};

// Approve a seller
const approveSeller = (id) => {
  return axios.post(`/api/admin/sellers/${id}/approve`);
};

// Reject a seller
const rejectSeller = (id) => {
  return axios.delete(`/api/admin/sellers/${id}`); // Replace with actual API endpoint
};

const getReviews = () => {
  return axios.get('/api/admin/reviews');
};

const deleteReview = (id) => {
  return axios.delete(`/api/admin/reviews/${id}`);
};


const adminService = {
  getSellers,
  approveSeller,
  rejectSeller,
  getReviews,
  deleteReview,
};

export default adminService;
