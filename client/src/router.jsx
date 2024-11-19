import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import Auth from "./features/Auth/Auth";
import ManageSeller from "./features/admin/ManageSeller";
import ManageReviews from "./features/admin/ManageReviews";
import AdminDashboard from "./features/admin/AdminDashboard"

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
    }
])

export default router