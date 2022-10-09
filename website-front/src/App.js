import React, { useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomePage from "./Components/HomePage";
import Login from "./Components/Login";
import Harta from "./Components/Harta";
import Orar from "./Components/Orar";

export default function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    return (
        <BrowserRouter >
            <Routes >
            <Route exact path = "/" element = { < HomePage isLoggedIn = { isLoggedIn }/>} />
            <Route path = "/login"
                element = { < Login setIsLoggedIn = { setIsLoggedIn }
            />} />
            <Route path = "/Harta" element = { < Harta />}/>
            <Route path = "/Orar" element = { < Orar /> } />
            </Routes>
            </BrowserRouter>
            );
    }