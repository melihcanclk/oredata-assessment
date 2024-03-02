import { FormEvent } from "react";
import { AuthLayout } from "./AuthLayout";
import { AuthInput } from "./AuthInput";
import { AuthButton } from "./AuthButton";
import { AuthLoginDTO } from "../../types/AuthLoginDTO";
// import { useHistory } from "react-router-dom";

const Login = () => {

    const handleLogin = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const target = e.target as HTMLFormElement;
        const values = {
            email: target.email.value,
            password: target.password.value,
        } as AuthLoginDTO;

        console.log(values.email, values.password);
    };

    return (
        <AuthLayout>
            <div>
                <h1 className="text-2xl text-center font-semibold">Login</h1>
            </div>
            <div className="divide-y divide-gray-200">
                <form onSubmit={handleLogin} className="py-8 text-base leading-6 space-y-4 text-gray-700 sm:text-lg sm:leading-7">
                    <AuthInput
                        label="Email"
                        id="loginInput"
                        type="text"
                        error=""
                    />
                    <AuthInput
                        label="Password"
                        id="password"
                        type="password"
                        error=""
                    />
                    <AuthButton loading={false} type="submit">
                        Login
                    </AuthButton>
                </form>
            </div>
        </AuthLayout>
    );
}

export { Login };

