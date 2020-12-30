import React, { Component } from 'react'
import { Card, Button, Alert } from 'react-bootstrap'
import {PieChart} from '../Charts/PieChart'
import { getCustomerGenderProfile } from '../../api'

class GenderGraph extends Component {
  constructor() {
    super()
    this.state = {
      result: [],
      city: ''
    }
    this.handleChange = this.handleChange.bind(this)
    this.handleClick = this.handleClick.bind(this)
  }

  handleChange(event) {
    this.setState({ city: event.target.value })
  }

  async handleClick(event) {
    const { value } = event.target
    if (value === '') {
      const { payload } = await getCustomerGenderProfile()
      this.setState({ result: payload })

    }else if (parseInt(value) <= 22 || parseInt >= 1) {
      const { payload } = await getCustomerGenderProfile(value)
      this.setState({ result: payload })
    }
  }

  async componentDidMount() {
    const fetched = await getCustomerGenderProfile()
    this.setState({ result: fetched.payload })
  }

  render() {
    const series = this.state.result.map(gender => gender.members)
    const chartData = {
      labels: ["Male", "Female", "Other"],
      series
    }

    return (
      <div>
        <Card>
          <Card.Body>
            <Card.Title>Gender Profile</Card.Title>
            <form>
              <label>
                city
              <input
                  type="text"
                  value={this.state.city}
                  onChange={this.handleChange}
                  placeholder="{1-22}" />
              </label>
              <Button value={this.state.city} onClick={this.handleClick} >click</Button>
            </form>
            <PieChart data={chartData} />
          </Card.Body>
          <Alert
            variant='warning'
            show={(parseInt(this.state.city) < 1) || (parseInt(this.state.city) > 22)} >
            Incorrect input. Input must be in '1-22' range.
          </Alert>
        </Card>
      </div>
    )
  }

}

export default GenderGraph