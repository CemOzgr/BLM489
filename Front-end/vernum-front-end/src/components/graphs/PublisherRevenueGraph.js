import React, { Component } from 'react'
import { Button, Card } from 'react-bootstrap'
import {LineChart} from '../Charts/LineChart'
import {getPublisherRevenue} from '../../api'

class PublisherRevenueGraph extends Component {

  constructor() {
    super()
    this.state = {
      result: [],
      publisher: "Activision"
    }
    this.handleChange = this.handleChange.bind(this)
    this.handleClick = this.handleClick.bind(this)
  }

  handleChange(event) {
    this.setState({ publisher: event.target.value })
  }

  async handleClick(event) {
    const publisher = event.target.value
    const fetched = await getPublisherRevenue(publisher)
    this.setState({ result: fetched.payload, publisher: publisher })
  }

  async componentDidMount() {
    const fetched = await getPublisherRevenue(this.state.publisher)
    this.setState({ result: fetched.payload })
  }

  render() {
    const labels = this.state.result.map(i => i.year)
    const series = this.state.result.map(i => i.revenue)

    const chartData = {
      labels,
      series: [series]
    }

    return (
      <div>
        <Card>
          <Card.Body>
            <Card.Title>{this.state.publisher} revenue</Card.Title>
            <form>
              <label>
                Publisher
              <input
                  type="text"
                  value={this.state.publisher}
                  onChange={this.handleChange}
                  placeholder="publisher" />
              </label>
              <Button value={this.state.publisher} onClick={this.handleClick} >click</Button>
            </form>
            <LineChart data={chartData} />
          </Card.Body>

        </Card>
      </div>
    )
  }

}

export default PublisherRevenueGraph