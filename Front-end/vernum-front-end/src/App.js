import React from "react"
import { BrowserRouter as Router, Route } from 'react-router-dom'
import Sidebar from './components/Sidebar'
import Main from './components/Main'


const App = () => {
  return (
    <div className="wrapper">
      <Router>
        <Sidebar />
        <Route path='/' component={Main}/>
      </Router>
    </div>
  )
}

export default App;
