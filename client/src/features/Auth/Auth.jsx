import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate from react-router-dom
import './Auth.css';
import authService from '../../services/authService';
import axios from 'axios';

const Auth = () => {
  const [isLogin, setIsLogin] = useState(true);
  const [email, setEmail] = useState('moe@gmai.com');
  const [password, setPassword] = useState('1111');
  const [role, setRole] = useState('Buyer');
  const [username, setUsername] = useState('Moe');
  const [error, setError] = useState('');

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    console.log("press submit");

    try {
      if (isLogin) {

        //const loginData = await authService.login(email, password);
        //console.log('Login success:', loginData);

        navigate(`/admin/manage-sellers`);
        //navigate(`/seller/manage-products`);
        // navigate(`/buyer/products`);

      } else {
        //const registerData = await authService.register(email, password, role, username);
        //console.log('Registration success:', registerData);

        setIsLogin(true)
      }
    } catch (err) {
      setError(`${isLogin ? 'Login' : 'Registration'} failed!`);
      console.error(err);
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-form">
        <h2>{isLogin ? 'Login' : 'Register'}</h2>

        {/* Show error message if exists */}
        {error && <div className="error-message">{error}</div>}

        <form onSubmit={handleSubmit}>
          {/* Username input for registration */}
          {!isLogin && (
            <div className="form-group">
              <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
              />
            </div>
          )}

          <div className="form-group">
            <input
              type="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>

          {/* Show radio buttons only during registration */}
          {!isLogin && (
            <div className="form-group">
              <label>Account Role:</label>
              <div className="role-selection">
                <label>
                  <input
                    type="radio"
                    name="role"
                    value="buyer"
                    checked={role === 'buyer'}
                    onChange={() => setRole('buyer')}
                  />
                  Buyer
                </label>
                <label>
                  <input
                    type="radio"
                    name="role"
                    value="seller"
                    checked={role === 'seller'}
                    onChange={() => setRole('seller')}
                  />
                  Seller
                </label>
              </div>
            </div>
          )}

          <button type="submit" className="auth-btn">
            {isLogin ? 'Login' : 'Register'}
          </button>
        </form>

        <div className="toggle-link">
          <span>
            {isLogin ? "Don't have an account?" : 'Already have an account?'}
          </span>
          <a href="#" onClick={() => setIsLogin(!isLogin)}>
            {isLogin ? 'Register here' : 'Login here'}
          </a>
        </div>
      </div>
    </div>
  );
};

export default Auth;
