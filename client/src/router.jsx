import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import Auth from "./features/Auth/Auth";

const router = createBrowserRouter([
    {
        path: "/",
        element: <App />, children: [
            { path: "auth", element: <Auth /> }
        ]
    }
])

export default router