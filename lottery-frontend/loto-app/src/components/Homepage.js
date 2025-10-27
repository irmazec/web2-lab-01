import LoginButton from '../components/LoginButton';
import LogoutButton from '../components/LogoutButton';
import UserInfo from '../components/UserInfo';
import RoundDetails from '../components/RoundDetails';
import { useAuth0 } from "@auth0/auth0-react";
import { useNavigate } from "react-router-dom";
import '../style/Homepage.css';

function Homepage({url}){
    const { isAuthenticated, isLoading } = useAuth0();
    const navigate = useNavigate();

    const navigateToTickets = () => {
        navigate("/app/ticket/pay");
    }

    if (isLoading) {
        return <div>Loading...</div>;
    }

    return (
        <div className="home">
            {!isAuthenticated ? (
                <div>
                    <h1>Auth0</h1>
                    <LoginButton />
                </div>
            ) : (
                <div className="homepage-div">
                    <div className="UserInfo">
                        <UserInfo />
                        <LogoutButton />
                    </div>
                    <div className="round-details">
                        <RoundDetails url={url}/>
                    </div>
                    <div className="pay-ticket">
                        <button onClick={navigateToTickets}>Igraj loto!</button>
                    </div>
                </div>
            )}
        </div>
    );

}

export default Homepage;