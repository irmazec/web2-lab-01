import { useState } from "react";
import { useAuth0 } from "@auth0/auth0-react";

const TicketDetails = ({ url }) => {
  const { isAuthenticated } = useAuth0();
  const [numbers, setNumbers] = useState("");
  const [userId, setUserId] = useState("");
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);

  if (!isAuthenticated) {
    return <p>Ne možete igrati ako niste ulogirani!</p>;
  }

  const onSubmit = (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage("");
    fetch(`${url}?userId=${encodeURIComponent(userId)}&numbers=${encodeURIComponent(numbers)}`, {
      method: "POST",
      })

      .then((response) => {
        if (!response.ok) throw new Error(`Greska: ${response.status}`);
        return response.json();
      })
      .then(() => {
        setNumbers("");
        setUserId("");
      })
      .catch((err) => {
        setMessage(`Greska: ${err.message}`);
      })
      .finally(() => {
        setLoading(false);
      });
  };

  return (
    <div className="play-loto">
      <form onSubmit={onSubmit}>
        <hr></hr>
         <label>
          Upisite broj osobne iskaznice/putovnice:
          <input
            type="text"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            required
          />
        </label>
        <hr></hr>
        <label>
          Upisite brojeve odvojene zarezom:
          <input
            type="text"
            value={numbers}
            onChange={(e) => setNumbers(e.target.value)}
            required
          />
        </label>
        <hr></hr>
        <button type="submit" disabled={loading}>
          Igraj
        </button>
      </form>
    </div>
  );
};

export default TicketDetails;
