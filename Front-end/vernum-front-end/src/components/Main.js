import React from 'react'
import Navbar from './Navbar'
import Footer from './Footer'
import Sidebar from './Sidebar'
import { Switch, Route } from 'react-router-dom'
import Dashboard from './Dashboard'
import { GameComponent } from './GameComponent'

const Main = props => {

  return (
    <div className="main-panel">
      <Navbar />
      <Sidebar />
      <Switch>
        <Route path="/dashboard" component={Dashboard} />
        <Route path="/games" component={GameComponent} />
      </Switch>
      <Footer />
    </div>
  )

}

export default Main