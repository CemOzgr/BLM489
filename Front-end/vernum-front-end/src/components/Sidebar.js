import React from "react"
import {NavLink, Link} from 'react-router-dom'

const Sidebar = () => {
  return (
    <div className="sidebar">
      <div className="sidebar-wrapper">
        <div className="logo">
          <Link to='/' className="simple-text">
            Vernum
          </Link>
        </div>
        <ul className="nav">
          <li className="nav-item">
            <NavLink className="nav-link" to='/dashboard'>
              <i className="nc-icon nc-chart-pie-35"></i>
              <p>Churn</p>
            </NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to='/games'>
              <i className="nc-icon nc-circle-09" ></i>
              <p>Game Sales</p>
            </NavLink>
          </li>
        </ul>
      </div>   
    </div>
  )
}

export default Sidebar