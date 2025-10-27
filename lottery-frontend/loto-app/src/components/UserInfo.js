import { useAuth0 } from "@auth0/auth0-react";

const UserInfo = () => {
    const { user } = useAuth0();
    return(
        <div className="user-info">
            <h1>Dobar dan, {user.name}!</h1>
            <h2>Email: {user.email}</h2>
        </div>
    )
}

export default UserInfo;