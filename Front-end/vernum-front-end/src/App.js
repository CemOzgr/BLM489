import React from "react"
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import Main from './components/Main'
import Login from "./login/Login"


const App = () => {
  return (
    <div className="wrapper">
      <Router>
        <Switch>
          <Route exact path='/login' component={Login} />
          <Route path='/' component={Main} />
        </Switch>
        
      </Router>
    </div>
  )
}

export default App;
