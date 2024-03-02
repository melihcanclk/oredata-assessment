import { FormEvent } from "react";
import { AuthLayout } from "../../components/AuthLayout";
import { AuthInput } from "../../components/AuthInput";
import { AuthButton } from "../../components/AuthButton";
import { AuthLoginDTO } from "../../types/AuthLoginDTO";
import { useStore } from "../../zustand";
import { IBear } from "../../zustand";
// import { useHistory } from "react-router-dom";

const Login = () => {

    const bears = useStore((state: unknown) => (state as IBear).bears);
    const increasePopulation = useStore((state: unknown) => (state as IBear).increasePopulation);
    console.log(bears);

    const handleLogin = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const target = e.target as HTMLFormElement;
        const values = {
            email: target.email.value,
            password: target.password.value,
        } as AuthLoginDTO;

        console.log(values.email, values.password);
        increasePopulation();
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
                        id="email"
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

