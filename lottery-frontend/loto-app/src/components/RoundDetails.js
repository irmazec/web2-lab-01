import { useEffect, useState } from "react";

function RoundDetails({ url }) {
  const [round, setRound] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchLastRound = async () => {
      try {
        const response = await fetch(`${url}/app/last-round`);
        if (!response.ok) throw new Error(`HTTP error: ${response.status}`);
        const data = await response.json();
        setRound(data);
      } catch (err) {
        setError(err.message);
      }
    };

    fetchLastRound();
  }, [url]);

  if (error) return <p>Greška: {error}</p>;
  if (!round) return <p>Nema aktivnih kola, vratite se kasnije.</p>;

  return (
    <div className="round-details">
      <h2>Trenutno kolo</h2>
      <p><strong>ID kola:</strong> {round.id}</p>
      <p><strong>Trenutno aktivno:</strong> {round.active ? "true" : "false"}</p>
      <p><strong>Broj listića:</strong> {round.tickets.length}</p>
    </div>
  );
}

export default RoundDetails;
