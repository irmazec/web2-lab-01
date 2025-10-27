import './App.css';
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Homepage from './components/Homepage';
import TicketDetails from './components/TicketDetails';

function App() {
  const backendUrl = process.env.REACT_APP_BACKEND_URL || 'http://localhost:8080';
  return (
        <Router>
            <div className="App">
            <Routes>
                <Route path="/app" element={<Homepage url={`${backendUrl}`}/>}/>
                <Route path="/app/ticket/pay" element={<TicketDetails url={`${backendUrl}app/ticket/pay`} />} />
            </Routes>
            </div>
        </Router>
  );
}

export default App;
