import { RiAccountCircleFill } from "react-icons/ri";
import { ImProfile } from "react-icons/im";
import { IoIosSettings } from "react-icons/io";
import { GoArrowSwitch } from "react-icons/go";

const menuItems = [
    { icon: <RiAccountCircleFill size={25} className="mx-2" />, text: "Account", href: "/account"},
    { icon: <ImProfile  size={25} className="mx-2" />, text: "Profile" , href: "/profile"},
    { icon: <GoArrowSwitch  size={25} className="mx-2" />, text: "Transactions" , href: "/transactions"},
    { icon: <IoIosSettings  size={25} className="mx-2" />, text: "Settings" , href: "/settings"},
];

export { menuItems };