import { FormEvent } from "react";
import { AuthInput } from "./AuthInput";
import { AuthLayout } from "./AuthLayout";
import { AuthButton } from "./AuthButton";

const Register = () => {

    function handleSubmit(event: FormEvent<HTMLFormElement>): void {
        event.preventDefault();
        const target = event.target as HTMLFormElement;
        const values = {
            username: target.username.value as AuthRegisterDTO['username'],
            email: target.email.value as AuthRegisterDTO['email'],
            password: target.password.value as AuthRegisterDTO['password'],
            confirmPassword: target.confirmPassword.value as AuthRegisterDTO['password'],
        }

        console.log('Form submitted',
            values.username,
            values.email,
            values.password,
            values.confirmPassword
        );
    }

    return (
        <AuthLayout>
            <div>
                <h1 className="text-2xl text-center font-semibold">Register</h1>
            </div>
            <div className="divide-y divide-gray-200 space-y-4">
                <form onSubmit={handleSubmit} className="py-8 text-base leading-6 space-y-4 text-gray-700 sm:text-lg sm:leading-7">
                    <AuthInput
                        label="Username"
                        id="username"
                        type="text"
                        error=""
                    />
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
                    <AuthInput
                        label="Confirm Password"
                        id="confirmPassword"
                        type="password"
                        error=""
                    />
                    <div className="mt-4">
                        <AuthButton type="submit">Register</AuthButton>
                    </div>
                </form>
            </div>
        </AuthLayout>
    );
}

export { Register };