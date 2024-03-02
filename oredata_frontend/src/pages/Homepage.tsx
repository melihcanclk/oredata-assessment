import { Link } from "react-router-dom";
import { TileProps } from "../types/TileProps";

const Homepage = () => {
    return (
        <DashboardGrid />
    );
}

const DashboardGrid = () => {
    return (
        <div className="w-full h-full">
            <div className="h-full p-8 items-center">
                {
                    // center
                }
                <div className="flex flex-col justify-center items-center h-full">
                    <div className="flex justify-center items-center">
                        <div>
                            <h1 className="text-2xl font-semibold">Welcome to the Dashboard</h1>
                            <p className="text-gray-500">Manage your account, transactions, and more</p>
                        </div>
                    </div>

                    <div className="w-full m-4">
                        <div className="grid grid-cols-2 gap-4">
                            <Tile
                                title="Account"
                                description="Manage your account"
                                href="/account"
                            />
                            <Tile
                                title="Transactions"
                                description="View your transactions"
                                href="/transactions"
                            />
                        </div>

                        <div className="grid grid-cols-2 gap-4 mt-4">
                            <Tile
                                title="Profile"
                                description="Manage your profile"
                                href="/profile"
                            />
                            <Tile
                                title="Settings"
                                description="Manage your settings"
                                href="/settings"
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

const Tile = ({ title, description, href }: TileProps) => {
    return (
        <Link to={href} className="bg-red-500 text-white p-4 rounded-md shadow-md">
            <h1 className="text-2xl font-semibold">{title}</h1>
            <p className="text-gray-200">{description}</p>
        </Link>
    );
}


export { Homepage };