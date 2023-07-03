import React from 'react'
import "./Navbar.css"

const Navbar = () => {
  return (
    <div className="navbar">
      <div className="container">
        <span className="logo">Booking</span>
        <div className='items'>
          <button className='button'>Register</button>
          <button className='button'>Login</button>
        </div>
      </div>
    </div>
  );
}

export default Navbar