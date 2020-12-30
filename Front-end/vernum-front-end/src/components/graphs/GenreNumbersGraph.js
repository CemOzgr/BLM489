import React, { Component } from 'react'
import { Card, Button } from 'react-bootstrap'
import { getGenres } from '../../api'
import { BarChart } from '../Charts/BarChart'

class GenreNumbersGraph extends Component {
  constructor() {
    super()
    this.state = {
      result: [],
      platform: ""
    }

    this.handleClick = this.handleClick.bind(this)
    this.handleChange = this.handleChange.bind(this)
  }

  handleChange(event) {
    this.setState({platform: event.target.value})
  }

  async handleClick(event) {
    const platform = event.target.value
    const fetched = await getGenres(platform)
    this.setState({result: fetched.payload, platform: platform})
  }

  async componentDidMount() {
    const fetched = await getGenres("all")
    this.setState({ result: fetched.payload })
  }

  render() {
    const data = this.state.result.slice(0, 5)
    const labels = data.map(i => i.genre)
    const series = data.map(i => i.count)

    const chartData = {
      labels,
      series: [series]
    }

    return (
      <div>
        <Card>
          <Card.Body>
            <Card.Title>Top 5 genres</Card.Title>
            <form>
              <label>
                platform   
              <input
                  type="text"
                  value={this.state.platform}
                  onChange={this.handleChange}
                  placeholder="platform" />
              </label>
              <Button value={this.state.platform} onClick={this.handleClick} >click</Button>
            </form>
            <BarChart data={chartData} />
          </Card.Body>
        </Card>
      </div>
    )
  }

}

export default GenreNumbersGraph