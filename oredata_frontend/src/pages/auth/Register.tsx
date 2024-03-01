import { AuthInput } from "./AuthInput";
import { AuthLayout } from "./AuthLayout";

const Register = () => {

    return (
        <AuthLayout>
            <div>
                <h1 className="text-2xl text-center font-semibold">Register</h1>
            </div>
            <div className="divide-y divide-gray-200">
                <form action="">
                    <AuthInput
                        label="Email"
                        type="text"
                        value=""
                        onChange={() => { }}
                        error=""
                    />
                </form>
            </div>
        </AuthLayout>
    );
}

export { Register };