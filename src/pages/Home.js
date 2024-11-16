import React from 'react';
import './Home.css';

const Home = () => {
  return (
    <div className="home-container">
      <div className="logo-section">
        <div className="logo-placeholder">LOGO OF BANK</div>
      </div>
      <div className="title-section">
        <h2>WELCOME TO LOCAL BANK</h2>
      </div>
      <div className="services-section">
        <button className="service-button">LOANS</button>
        <button className="service-button">INVESTMENTS</button>
        <button className="service-button">INSURANCE</button>
      </div>
    </div>
  );
};

export default Home;
