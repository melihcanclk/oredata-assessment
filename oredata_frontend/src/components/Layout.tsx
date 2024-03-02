import { Outlet } from "react-router-dom";
import Sidebar from "./Sidebar";

export const Layout = () => {
    return (
        <div>
            <Sidebar />
            <div className="flex">
                <div className="w-full">
                    <Outlet />
                </div>
            </div>
        </div>
    );
}