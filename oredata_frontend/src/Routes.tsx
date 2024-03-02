import { Route, RouterProvider, createBrowserRouter, createRoutesFromElements } from "react-router-dom"
import Error404 from "./pages/404"
import { Homepage } from "./pages/Homepage"
import { Login } from "./pages/auth/Login"
import { Register } from "./pages/auth/Register"
import { Profile } from "./pages/Profile"
import { Transactions } from "./pages/Transactions"
import { Layout } from "./components/Layout"
import { Settings } from "./pages/Settings"
import { Account } from "./pages/Account"

const routes = createBrowserRouter(
    createRoutesFromElements(
        <Route path="/" >
            <Route element={<Layout />} >
                <Route index element={<Homepage />} />
                <Route path="profile" element={<Profile />} />
                <Route path="transactions" element={<Transactions />} />
                <Route path="settings" element={<Settings />} />
                <Route path="account" element={<Account />} />
            </Route>
            <Route path="login" element={<Login />} />
            <Route path="register" element={<Register />} />
            <Route path="*" element={<Error404 />} />
        </Route >
    )
)

export const AppRoutes = () => {
    return (
        <RouterProvider router={routes} />
    )
}