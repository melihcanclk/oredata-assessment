import { AuthInputProps } from "../types/AuthInputProps";

const AuthInput = ({ label, id, type, value, onChange, error }: AuthInputProps) => {
    return (
        <div className="relative">
            <input autoComplete="off" id={id} name={label} type={type} className="peer placeholder-transparent h-10 w-full border-b-2 border-gray-300 text-gray-900 focus:outline-none focus:borer-rose-600" placeholder={label} />
            <label htmlFor={label} className="absolute left-0 -top-3.5 text-gray-600 text-sm peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-440 peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm">{label}</label>
            {error && <div className="text-red-500 text-xs">{error}</div>}
        </div>
    );
}

export { AuthInput }