import { Link } from 'react-router-dom'

export default function AdminDashboard() {

    const logout = () => {
        // logout logic
    }

    return (
        <div>
            <div style={{
                padding: 30,
            }}>
                <p
                    style={{
                        fontSize: 20,
                        fontWeight: 'bold',
                        color: 'red',
                    }}>Admin Dashboard</p>

                <p
                    style={{
                        marginTop: 20,
                        fontSize: 16,
                        color: 'gray',
                    }}>
                    This should be a dashboard page for admin.
                </p>
                <div
                    style={{
                        marginTop: 20,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                        gap: 10,
                    }} >
                    <Link to='/'>
                        <button >Back to homepage</button>
                    </Link>
                    <button onClick={logout}>Sign Out</button>
                </div>
            </div>
        </div>
    )
}