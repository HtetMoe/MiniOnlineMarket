import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import Auth from "./features/Auth/Auth";

import ManageSeller from "./features/admin/ManageSeller";
import ManageReviews from "./features/admin/ManageReviews";
import AdminDashboard from "./features/admin/AdminDashboard"

import SellerDashboard from './features/seller/SellerDashboard'
import ManageProducts from './features/seller/ManageProducts'
import ManageOrders from './features/seller/ManageOrders'

const router = createBrowserRouter([
    {
        path: "/",
        element: <App />, children: [
            { path: "auth", element: <Auth /> }
        ]
    },
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
    }
])

export default router