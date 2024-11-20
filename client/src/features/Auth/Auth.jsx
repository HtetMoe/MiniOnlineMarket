import React, { useState } from 'react';
import './Auth.css';

const Auth = () => {
  const [isLogin, setIsLogin] = useState(true);
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('buyer'); // Role state for buyer or seller

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isLogin) {
        // Handle Login logic here
      } else {
        // Handle Registration logic here, including the selected role
        console.log(`Email: ${email}, Password: ${password}, Role: ${role}`);
        // You can send this data to your backend for registration
      }
    } catch (error) {
      alert(`${isLogin ? 'Login' : 'Registration'} failed!`, error);
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-form">
        <h2>{isLogin ? 'Login' : 'Register'}</h2>

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
        
        <form onSubmit={handleSubmit}>
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
