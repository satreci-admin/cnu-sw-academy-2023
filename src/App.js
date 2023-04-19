import React from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import "./App.css";

import Sidebar from "./components/Sidebars";
import Robot from "./pages/Robot";
import Work from "./pages/Work";
import Member from "./pages/Member";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Sidebar />
        <Routes>
          <Route path="/" element={<Navigate to="/robot" />} />
          <Route path="/robot" element={<Robot />} />
          <Route path="/work-specification" element={<Work />} />
          <Route path="/member" element={<Member />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
