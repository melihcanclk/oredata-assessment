import { Button } from "../../components/Button"
import { ButtonProps } from "../../types/AuthButtonProps"

const AuthButton = ({ loading, children }: ButtonProps) => {

    return (
        loading ?
            <>
                <span className="absolute inset-y-0 left-0 flex items-center pl-3">
                    <svg className="h-5 w-5 text-indigo-500 group-hover:text-indigo-400" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true"></svg>
                </span>
                <Button
                    type="submit"
                >
                    Loading..
                </Button>
            </>
            : (
                <Button
                    type="submit"
                >
                    {children}
                </Button>
            )
    )
}

export { AuthButton }