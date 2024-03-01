import { Route, RouterProvider, createBrowserRouter, createRoutesFromElements } from "react-router-dom"
import Error404 from "./pages/404"
import AdminDashboard from "./pages/AdminDashboard"
import { Homepage } from "./pages/Homepage"

const routes = createBrowserRouter(
    createRoutesFromElements(
        <Route path="/" >,
            <Route index element={<Homepage />} />,
            <Route path="admin" element={<AdminDashboard />} />,
            <Route path="*" element={<Error404 />} />
        </Route >
    )
)

export const AppRoutes = () => {
    return (
        <RouterProvider router={routes} />
    )
}