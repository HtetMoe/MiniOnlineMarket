import axios from 'axios';

const API_URL = 'http://localhost:8005/api/v1';

const register = async (email, password, role, fullName) => {
  console.log('email: ', email, ', password: ', password, ', full name: ', fullName, ', Role: ', role)
  
  try {
    const response = await axios.post(`${API_URL}/sellers/`, { email, password, fullName });

    // Store the user info and token in localStorage if registration is successful
    if (response.data) {
      localStorage.setItem('user', JSON.stringify(response.data));
    }
    return response.data;
  } catch (error) {
    console.error('Registration error', error);
    throw error;
  }
}

const login = async (email, password) => {
  // try {
  //   const response = await axios.post(`${API_URL}/login`, { email, password });
  //   if (response.data.token) {
  //     localStorage.setItem('user', JSON.stringify(response.data)); // Save the token and user info
  //   }
  //   return response.data;
  // } catch (error) {
  //   console.error('Login error', error);
  //   throw error;
  // }
};

const logout = () => {
  localStorage.removeItem('user'); // Clear the user data from localStorage
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem('user')); // Return the user info from localStorage
};

const getToken = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  return user ? user.token : null; // Return the token if it exists
};

export default {
  register,
  login,
  logout,
  getCurrentUser,
  getToken
};

/*

//register
    {
    "id": 2,
    "email": "johndoe@email.com",
    "fullName": "Full name",
    "role": "SELLER",
    "isApproved": false,
    "products": []
    }


    */