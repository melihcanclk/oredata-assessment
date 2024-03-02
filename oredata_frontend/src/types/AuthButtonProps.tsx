export interface ButtonProps {
    children: React.ReactNode;
    type: "submit" | "button" | "reset" | undefined;
    loading?: boolean;
}