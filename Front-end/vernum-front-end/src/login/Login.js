import React from "react"
import { Form, FormGroup, Button } from "react-bootstrap"
import {login} from '../api'
import './login.css'

class Login extends React.Component {

  constructor() {
    super()
    this.state = {
      username: "",
      password: ""
    }
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  validate() {
    return (this.state.username.length > 0 && this.state.password.length > 0)
  }

  async handleSubmit(event) {
    event.preventDefault()
    const {data} = await login(this.state.username, this.state.password)

    if(data === 204)
      this.props.history.push('/dashboard')
  }

  render() {
    return (
      <div className="Login">
        <Form onSubmit={this.handleSubmit}>
          <FormGroup size="lg" controlId="username">
            <Form.Label>username</Form.Label>
            <Form.Control
              autoFocus
              type="username"
              value={this.state.username}
              onChange={(event) => this.setState({username:event.target.value})}
            />
          </FormGroup>
          <FormGroup size="lg" controlId="password">
            <Form.Label>password</Form.Label>
            <Form.Control
              type="password"
              value={this.state.password}
              onChange={(event) => this.setState({password:event.target.value})}
            />
          </FormGroup>
          <Button block size="lg" type="submit" disabled={!this.validate()}>
            LOGIN
          </Button>
        </Form>
      </div>
    )
  }
  

}

export default Login