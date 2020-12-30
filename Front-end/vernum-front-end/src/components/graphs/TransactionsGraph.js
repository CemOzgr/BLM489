import React, { Component } from 'react'
import { Card, Button, Alert } from 'react-bootstrap'
import { getTransactions } from '../../api'
import { BarChart } from '../Charts/BarChart'
import { LineChart } from '../Charts/LineChart'

class TransactionGraph extends Component {

  constructor() {
    super()
    this.state = {
      result: [],
      years: [],
      year: '',
      chartType: "Bar"
    }
    this.handleClick = this.handleClick.bind(this)
    this.handleChange = this.handleChange.bind(this)
  }

  async componentDidMount() {
    const fetched = await getTransactions()
    const years = fetched.payload.map(r => r.t_date)
    this.setState({ result: fetched.payload, years: years})
  }

  handleChange(event) {
    this.setState({ year: event.target.value })
  }

  async handleClick(event) {
    const year = event.target.value

    if (this.state.years.includes(parseInt(year))) {
      const { payload } = await getTransactions(year)
      this.setState({
        result: payload,
        year: year,
        chartType: "Line"
      })
    } else if (year === "total") {
      const { payload } = await getTransactions()
      this.setState({
        result: payload,
        year: '',
        chartType: "Bar"
      })
    }

  }

  render() {
    const labels = this.state.result.map(r => r.t_date)
    const series = this.state.result.map(r => r.transactions / 100000)

    const chartData = {
      labels,
      series: [series]
    }
    const year = this.state.year
    return (
      <div>
        <Card>
          <Card.Body>
            <Card.Title>Transactions</Card.Title>
            <form>
              <label>
                year
              <input
                  type="text"
                  value={this.state.year}
                  onChange={this.handleChange}
                  placeholder="enter a year" />
              </label>
              <Button value={this.state.year} onClick={this.handleClick} >click</Button>
            </form>
            <div>
              {this.state.chartType === "Bar"
                ? <BarChart data={chartData} />
                : <LineChart data={chartData} />
              }
            </div>
            <Alert 
              variant="warning"
              show={!(this.state.years.includes(parseInt(year)) || ['','total'].includes(year) )}>
                Incorret Input..
              </Alert>
          </Card.Body>
        </Card>
      </div>
    )
  }
}
export default TransactionGraph