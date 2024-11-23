import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import Auth from "./features/Auth/Auth";

import ManageSeller from "./features/admin/ManageSeller";
import ManageReviews from "./features/admin/ManageReviews";
import AdminDashboard from "./features/admin/AdminDashboard"

import SellerDashboard from './features/seller/SellerDashboard'
import ManageProducts from './features/seller/ManageProducts'
import ManageOrders from './features/seller/ManageOrders'
import BuyerDashboard from './features/buyer/BuyerDashboard'
import BuyerOrders from './features/buyer/BuyerOrders'
import ShoppingCart from './features/buyer/ShoppingCart'
import ProductReviews from './features/buyer/ProductReviews'
import ReviewSubmission from "./features/buyer/ReviewSubmission";
import OrderDetail from "./features/buyer/OrderDetail";
import BuyerProductList from "./features/buyer/BuyerProductList";
import ProductDetail from "./features/buyer/ProductDetail";

const router = createBrowserRouter([
    {
        path: "/",
        element: <App />, children: [
            { path: "auth", element: <Auth /> }
        ]
    },
    { path: "/auth", element: <Auth />},
    {
        path: "/admin",
        element: <AdminDashboard />,
        children: [
            { path: "manage-sellers", element: <ManageSeller /> },
            { path: "manage-reviews", element: <ManageReviews /> }
        ]
    },
    {
        path: "/seller",
        element: <SellerDashboard />,
        children: [
            { path: "manage-products", element: <ManageProducts /> },
            { path: "manage-orders", element: <ManageOrders /> }
        ]
    },
    {
        path: "/buyer",
        element: <BuyerDashboard />,
        children: [
            { path: "orders", element: <BuyerOrders /> },
            { path: "cart", element: <ShoppingCart /> },
            { path: "reviews", element: <ProductReviews /> },
            { path: "/buyer/products", element: <BuyerProductList /> }
        ]
    },
    {
        path: "/buyer/review/:orderId", element: <ReviewSubmission />
    },
    {
        path: "/buyer/order-detail/:orderId", element: <OrderDetail />
    },
    {
        path: "/buyer/product/:id", element: <ProductDetail />
    }
])

export default router