import React, { useState, useEffect } from 'react';
import './ManageProducts.css';
import sellerService from '../../services/sellerService.jsx';
import userData from '../../services/UserData.jsx';

const ManageProducts = () => {
  const [products, setProducts] = useState([])

  const [newProduct, setNewProduct] = useState({ name: '', price: '', stockQuantity: '', categoryName: '', description: '', categoryId: 0 }); //image: ''
  const [editingProduct, setEditingProduct] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  //fetch product
  useEffect(() => {
    const fetchProducts = async () => {
      if (userData) {
        try {
          const sellerID = userData.id
          const response = await sellerService.getSellerProducts(sellerID);
          setProducts(response.data);
        } catch (error) {
          console.error('Error fetching products:', error);
        }
      }
    };
    fetchProducts();
  }, []);

  const handleSubmitProduct = async (e) => {
    e.preventDefault();
    if (userData) {
      const sellerID = userData.id
      if (editingProduct) {
        try {
          // Update existing product
          const response = await sellerService.updateProduct(editingProduct.productId, newProduct);
          console.log('updated : ', response.data)
          setProducts(products.map(product =>
            product.productId === editingProduct.productId ? response.data : product
          ));
        } catch (error) {
          console.error('Error updating product:', error);
        }
      } else {
        try {
          // Add new product
          const product = await sellerService.createProduct(newProduct, sellerID);
          setProducts([...products, product.data]);
        } catch (error) {
          console.error('Error adding product:', error);
        }
      }
    }
    setIsModalOpen(false);
    setNewProduct({ name: '', price: '', stockQuantity: '', categoryName: '', description: '', categoryId: 0 });
    setEditingProduct(null);
  };

  const handleAddProduct = () => {
    setIsModalOpen(true);  // Open the modal
    setEditingProduct(null);  // Ensure we're not editing an existing product
    setNewProduct({ name: '', price: '', stockQuantity: '', categoryName: '', description: '', categoryId: 0 }); // Reset the form
  };

  const handleEditProduct = (product) => {
    setIsModalOpen(true); // Open the modal
    setEditingProduct(product); // Set the product for editing
    setNewProduct({ name: product.name, price: product.price, stockQuantity: product.stockQuantity, categoryName: product.categoryName, description: product.description, categoryId: 0 });
  };

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setNewProduct({ ...newProduct, image: URL.createObjectURL(file) });
    }
  };

  const handleDelete = async (productId) => {
    try {
      await sellerService.deleteProduct(productId);
      setProducts(products.filter(product => product.productId !== productId));
    } catch (error) {
      console.error('Error deleting product:', error);
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setNewProduct({ name: '', price: '', stockQuantity: '', categoryName: '', description: '' });
    setEditingProduct(null);
  };

  return (
    <div className="product-management-container">

      {/* Button to add new product */}
      <button onClick={handleAddProduct} className="add-product-btn">Add New Product</button>

      {/* Displaying the Existing Products */}
      <div>
        <h3>Existing Products</h3>
        <ul className="product-list">
          {products.map((product) => (
            <li key={product.productId} className="product-item">
              <div className="product-image-container">
                <img src={'https://via.placeholder.com/100'} alt={product.name} className="product-image" />
              </div>
              <div className="product-details">
                <strong>{product.name}</strong>
                <p>${product.price}</p>
                <p>{product.stockQuantity > 0 ? `${product.stockQuantity} in stock` : <span className="out-of-stock">Out of Stock</span>}</p>
                {/* Display category */}
                <p><strong>Category:</strong> {product.categoryName}</p>
              </div>
              <div className="button-container">
                <button className="edit" onClick={() => handleEditProduct(product)}>Edit</button>
                <button className="delete" onClick={() => handleDelete(product.productId)} disabled={product.stockQuantity === 0}>Delete</button>
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
                value={newProduct.stockQuantity}
                onChange={(e) => setNewProduct({ ...newProduct, stockQuantity: e.target.value })}
                required
              />
              {/* New category and description fields */}
              <input
                type="text"
                placeholder="Category"
                value={newProduct.categoryName}
                onChange={(e) => setNewProduct({ ...newProduct, categoryName: e.target.value })}
                required
              />
              <textarea
                placeholder="Product Description"
                value={newProduct.description}
                onChange={(e) => setNewProduct({ ...newProduct, description: e.target.value })}
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
