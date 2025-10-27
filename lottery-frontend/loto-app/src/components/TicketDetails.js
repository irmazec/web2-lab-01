import { useState } from "react";
import { useAuth0 } from "@auth0/auth0-react";

const TicketDetails = ({url}) => {
    const {isAuthenticated} = useAuth0();
    const [numbers, setNumbers] = useState("");
    const [userId, setUserId] = useState(null);
    const [message, setMessage] = useState("");

    if (!isAuthenticated){
        return <p>Ne mozete igrati ako niste ulogirani!</p>
    }

    const onSubmit = () => {
        try{
                const requestBody = {
                    userId: userId,
                    numbers: numbers
                }

                const response = fetch(url, {
                method: "POST",
                headers: {
                "Content-Type": "application/json"
                },
                body: JSON.stringify(requestBody)
            });
        }catch(err){
            setMessage(err);
        }
    }

    return(
    <div className="play-loto">
      <form onSubmit={onSubmit}>
        <label>
            Upisite broj osobne iskaznice/putovnice:
            <input
            type="text"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            required
            />
        </label>
        <label>
          Upisite brojeve odvojene zarezom:
          <input
            type="text"
            value={numbers}
            onChange={(e) => setNumbers(e.target.value)}
            required
          />
        </label>
        <button type="submit">Igraj</button>
      </form>
    </div>
    )
}

export default TicketDetails;