import { Outlet } from "react-router-dom";
import Sidebar from "./Sidebar";

export const Layout = () => {
    return (
        <div>
            <Sidebar />
                <div className="w-full bg-gray-100 p-3 h-[calc(100vh-74px)]">
                    <Outlet />
                </div>
        </div>
    );
}