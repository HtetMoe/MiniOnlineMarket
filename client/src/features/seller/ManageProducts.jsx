import React from 'react'
import { useState, useEffect } from 'react';
import './ManageProducts.css'

const ManageProducts = () => {
  const [products, setProducts] = useState([
    { name: 'Item 1', price: '$1000', quantity: 10 },
    { name: 'Item 2', price: '$2000', quantity: 22 },
    { name: 'Item 3', price: '$3000', quantity: 0 }
  ]);
  const [newProduct, setNewProduct] = useState(
    { name: 'Item 1', price: '$1000', quantity: 10 },
    { name: 'Item 2', price: '$2000', quantity: 22 },
    { name: 'Item 3', price: '$3000', quantity: 5 }
  );
  const [editingProduct, setEditingProduct] = useState(null);

  //fetch data
  useEffect(() => {
  }, []);

  const handleSubmitProduct = async (e) => {
    e.preventDefault();

    if (editingProduct) {
      //update product
    } else {
      //add new product
    }
  }

  //edit existing product 
  const handleEditProduct = (product) => {
    setEditingProduct(product);  // Set product to edit
    setNewProduct({
      name: product.name,
      price: product.price,
      quantity: product.quantity
    });
  }
  const handleDelete = async (id) => {
  }

  return (
    <div className="product-management-container">
      <h2>Product Management</h2>

      {/* Form for Adding or Editing a Product */}
      <form onSubmit={handleSubmitProduct}>
        <input
          type="text"
          placeholder="Product Name"
          value={newProduct.name}
          onChange={(e) => setNewProduct({ ...newProduct, name: e.target.value })}
          required
        />
        <input
          type="number"
          placeholder="Price"
          value={newProduct.price}
          onChange={(e) => setNewProduct({ ...newProduct, price: e.target.value })}
          required
        />
        <input
          type="number"
          placeholder="Stock Quantity"
          value={newProduct.quantity}
          onChange={(e) => setNewProduct({ ...newProduct, quantity: e.target.value })}
          required
        />
        <button type="submit">{editingProduct ? 'Update' : 'Add Product'}</button>
      </form>

      {/* Displaying the Existing Products */}
      <div>
        <h3>Existing Products</h3>
        <ul>
          {products.map((product) => (
            <li key={product.id}>
              <div className="product-card">
                <strong>{product.name}</strong> - ${product.price} -
                {product.quantity > 0 ? `${product.quantity} in stock` : <span>Out of Stock</span>}

                {/* Edit and Delete Button */}
                <div className="button-container">
                  <button className="edit" onClick={() => handleEditProduct(product)}>Edit</button>
                  <button className="delete" onClick={() => handleDelete(product.id)} disabled={product.quantity === 0}>
                    Delete
                  </button>
                </div>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  )
}

export default ManageProducts