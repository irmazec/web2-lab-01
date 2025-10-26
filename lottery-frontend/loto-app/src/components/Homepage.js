import LoginButton from '../components/LoginButton';
import LogoutButton from '../components/LogoutButton';

function Homepage(){
    return (
        <div className="home">
            <LoginButton />
            <LogoutButton />
        </div>
    )
}

export default Homepage;