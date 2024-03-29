import { useState } from "react";
import { AiOutlineClose, AiOutlineMenu, AiOutlineSearch, AiOutlineTransaction } from "react-icons/ai";
import { CgProfile } from "react-icons/cg";
import { Link } from "react-router-dom";
import { menuItems } from "../data/menuItems";

const Sidebar = () => {
    const [nav, setNav] = useState(false);

    const toggleNav = () => {
        setNav(!nav);
    }

    return (
        <div className="mx-auto flex justify-between items-center p-4 shadow-sm">
            {/* Left side */}
            <div className="flex items-center">
                <div onClick={toggleNav} className="cursor-pointer">
                    <AiOutlineMenu size={30} />
                </div>
                <Logo />
            </div>

            {/* Search Input */}
            <div className="bg-gray-200 rounded-full flex items-center px-2 w-[200px] sm:w-[300px] lg:w-[500px]">
                <AiOutlineSearch size={25} />
                <input
                    className="bg-transparent p-2 w-full focus:outline-none"
                    type="text"
                    placeholder="Search accounts"
                />
            </div>
            {/* Profile button */}
            <button className="bg-black text-white hidden sm:flex items-center py-2 rounded-full border border-black px-5 ">
                <CgProfile size={20} className="mr-2" /> Profile
            </button>

            {/* Mobile Menu */}
            {/* Overlay */}
            {nav ? (
                <div className="bg-black/80 fixed w-full h-screen z-10 top-0 left-0"></div>
            ) : (
                ""
            )}

            {/* Side drawer menu */}
            <div
                className={
                    nav
                        ? "fixed top-0 left-0 w-[300px] h-screen bg-white z-10 duration-300"
                        : "fixed top-0 left-[-100%] w-[300px] h-screen bg-white z-10 duration-300"
                }
            >
                <AiOutlineClose
                    onClick={() => setNav(!nav)}
                    size={30}
                    className="absolute right-4 top-4 cursor-pointer"
                />
                <div className="p-4">
                    <Logo />
                </div>
                <nav>
                    <ul className="flex flex-col p-4 text-gray-800">
                        {menuItems.map(({ icon, text, href }, index) => {
                            return (
                                <div key={index} className="py-4">
                                    <li className="text-xl flex cursor-pointer w-[60%] rounded-full mx-auto p-2 hover:text-white hover:bg-black">
                                        <Link to={href} className="flex items-center">
                                        {icon} {text}
                                        </Link>
                                    </li>
                                </div>
                            );
                        })}
                        {/* show cart button if small screen */}
                        <div className="flex justify-center py-4">
                            <li className="text-xl flex cursor-pointer w-[50%] rounded-full mx-auto p-2 hover:text-white hover:bg-black sm:hidden">
                                <CgProfile size={20} className="mr-2" /> Profile
                            </li>
                        </div>
                    </ul>
                </nav>
            </div>
                        
        </div>
    );
};

const Logo = () => (
    <Link to={"/"} className="pl-4">
        <span
            className="text-red-500 cursor-pointer hover:text-red-600 transition duration-300 ease-in-out text-2xl sm:text-3xl lg:text-4xl"
        >
            Cilek
        </span>{" "} <span className="font-bold text-2xl sm:text-3xl lg:text-4xl">Bank</span>
    </Link>
    
)

export default Sidebar;
