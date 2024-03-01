import { Route, RouterProvider, createBrowserRouter, createRoutesFromElements } from "react-router-dom"
import Error404 from "./pages/404"
import { Homepage } from "./pages/Homepage"
import { Login } from "./pages/auth/Login"
import { Register } from "./pages/auth/Register"

const routes = createBrowserRouter(
    createRoutesFromElements(
        <Route path="/" >,
            <Route index element={<Homepage />} />,
            <Route path="login" element={<Login />} />,
            <Route path="register" element={<Register />} />,
            <Route path="*" element={<Error404 />} />
        </Route >
    )
)

export const AppRoutes = () => {
    return (
        <RouterProvider router={routes} />
    )
}