import React, { useState, useEffect } from 'react';
import './ManageProducts.css';
import sellerService from '../../services/sellerService.jsx';

const ManageProducts = () => {
  const [products, setProducts] = useState([
    { id: 1, name: 'Item 1', price: '1000', quantity: 10, image: '' },
    { id: 2, name: 'Item 2', price: '2000', quantity: 22, image: '' },
    { id: 3, name: 'Item 3', price: '3000', quantity: 0, image: '' }
  ]);

  const [newProduct, setNewProduct] = useState({ name: '', price: '', quantity: '', image: '' });
  const [editingProduct, setEditingProduct] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    // const fetchProducts = async () => {
    //   try {
    //     const data = await sellerService.getSellerProducts();
    //     setProducts(data);
    //   } catch (error) {
    //     console.error('Error fetching products:', error);
    //   }
    // };
    // fetchProducts();
  }, []);

  // Handle submit for adding or editing a product
  const handleSubmitProduct = async (e) => {
    e.preventDefault();
    // if (editingProduct) {
    //   // Update existing product
    //   try {
    //     const updatedProduct = await sellerService.updateProduct(editingProduct.id, newProduct);
    //     setProducts(products.map(product =>
    //       product.id === editingProduct.id ? updatedProduct : product
    //     ));
    //   } catch (error) {
    //     console.error('Error updating product:', error);
    //   }
    // } else {
    //   // Add new product
    //   try {
    //     const product = await sellerService.createProduct(newProduct);
    //     setProducts([...products, product]);
    //   } catch (error) {
    //     console.error('Error adding product:', error);
    //   }
    // }
    setIsModalOpen(false);
    setNewProduct({ name: '', price: '', quantity: '', image: '' });
    setEditingProduct(null);
  };

  // Open modal for adding new product
  const handleAddProduct = () => {
    setIsModalOpen(true);  // Open the modal
    setEditingProduct(null);  // Ensure we're not editing an existing product
    setNewProduct({ name: '', price: '', quantity: '', image: '' }); // Reset the form
  };

  // Open modal for editing existing product
  const handleEditProduct = (product) => {
    setIsModalOpen(true); // Open the modal
    setEditingProduct(product); // Set the product for editing
    setNewProduct({ name: product.name, price: product.price, quantity: product.quantity, image: product.image });
  };

  // Handle image selection
  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setNewProduct({ ...newProduct, image: URL.createObjectURL(file) });
    }
  };

  // Handle delete product
  const handleDelete = async (id) => {
    try {
      await sellerService.deleteProduct(id);
      setProducts(products.filter(product => product.id !== id));
    } catch (error) {
      console.error('Error deleting product:', error);
    }
  };

  // Close modal
  const handleCloseModal = () => {
    setIsModalOpen(false);
    setNewProduct({ name: '', price: '', quantity: '', image: '' });
    setEditingProduct(null);
  };

  return (
    <div className="product-management-container">
      <h2>Product Management</h2>

      {/* Button to add new product */}
      <button onClick={handleAddProduct} className="add-product-btn">Add New Product</button>

      {/* Displaying the Existing Products */}
      <div>
        <h3>Existing Products</h3>
        <ul className="product-list">
          {products.map((product) => (
            <li key={product.id} className="product-item">
              <div className="product-image-container">
                <img src={product.image || 'https://via.placeholder.com/100'} alt={product.name} className="product-image" />
              </div>
              <div className="product-details">
                <strong>{product.name}</strong>
                <p>${product.price}</p>
                <p>{product.quantity > 0 ? `${product.quantity} in stock` : <span className="out-of-stock">Out of Stock</span>}</p>
              </div>
              <div className="button-container">
                <button className="edit" onClick={() => handleEditProduct(product)}>Edit</button>
                <button className="delete" onClick={() => handleDelete(product.id)} disabled={product.quantity === 0}>Delete</button>
              </div>
            </li>
          ))}
        </ul>
      </div>

      {/* Modal for Add/Edit Product */}
      {isModalOpen && (
        <div className="modal-overlay">
          <div className="modal-content">
            <h3>{editingProduct ? 'Edit Product' : 'Add New Product'}</h3>
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
              <input
                type="file"
                accept="image/*"
                onChange={handleImageChange}
              />
              {newProduct.image && <img src={newProduct.image} alt="Product preview" className="image-preview" />}
              <button type="submit">{editingProduct ? 'Update Product' : 'Add Product'}</button>
              <button type="button" onClick={handleCloseModal} className="close-modal-btn">Close</button>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default ManageProducts;
