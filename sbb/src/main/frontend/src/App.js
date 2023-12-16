// src/main/frontend/src/App.js
import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import "./index.css";
import SiteNav from './components/SiteNav.js';
import Main from './pages/Main.js'
import CreateRoom from './pages/CreateRoom.js';
import Login from './components/Login.js';
import Signup from './components/Signup.js';
import RoomDetail from './pages/RoomDetail.js';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
      <div>
        <SiteNav />
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Main />} />
                <Route path="/room/create" element={<CreateRoom />} />
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<Signup />} />
                <Route path="/room/detail/:no" element={<RoomDetail />} />
            </Routes>
        </BrowserRouter>
      </div>
  );
}

export default App;