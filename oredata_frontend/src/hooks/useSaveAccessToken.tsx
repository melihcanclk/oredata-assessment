export const useAccessToken = () => {
    const saveAccessToken = (token: string, value: string) => {
        localStorage.setItem(token, value);
    };

    const getAccessToken = (token: string) => {
        return localStorage.getItem(token);
    }

    return { saveAccessToken , getAccessToken};
}