import React, { Component } from 'react'
import { Card, Button, Table } from 'react-bootstrap'
import { getBestSellers } from '../../api'

class BestSellersTable extends Component {
  constructor() {
    super()
    this.state = {
      result: [],
      platform: "all"
    }
    this.handleChange = this.handleChange.bind(this)
    this.handleClick = this.handleClick.bind(this)
  }

  handleChange(event) {
    this.setState({ platform: event.target.value })
  }

  async handleClick(event) {
    const platform = event.target.value
    const fetched = await getBestSellers(platform)
    this.setState({ result: fetched.payload, platform: platform })
  }

  async componentDidMount() {
    const fetched = await getBestSellers(this.state.platform)
    this.setState({ result: fetched.payload })
  }

  render() {
    return (
      <Card>
        <Card.Body>
          <Card.Title>Best Sellers on {this.state.platform}</Card.Title>
          <div>
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
            <Table
              responsive
              stripped hover
              borderles={true}>
              <thead>
                <tr>
                  <th>Game</th>
                  <th>Genre</th>
                  <th>Year</th>
                  <th>Publisher</th>
                  <th>Sales(in millions)</th>
                </tr>
              </thead>
              <tbody>
                {this.state.result.map(i =>
                  <tr>
                    {Object.values(i).map(field =>
                      <td>{field}</td>)}
                  </tr>)}
              </tbody>
            </Table>
          </div>
        </Card.Body>

      </Card>

    )
  }

}

export default BestSellersTable