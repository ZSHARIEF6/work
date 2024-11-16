import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';

const Navbar = () => (
  <nav>
    <h1>LOCAL BANK</h1>
    <ul>
      <li><Link to="/about">About Us</Link></li>
      <li><Link to="/help">Help</Link></li>
      <li><Link to="/signup">Sign Up</Link></li>
      <li><Link to="/login">Login</Link></li>
    </ul>
  </nav>
);

export default Navbar;
