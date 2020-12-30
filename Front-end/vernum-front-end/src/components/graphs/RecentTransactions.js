import React, { Component } from 'react'
import { Card } from 'react-bootstrap'
import { getRecentTransactions } from '../../api'

class RecentTransactions extends Component {

  constructor() {
    super()
    this.state = {
      result: []
    }
  }

  async componentDidMount() {
    const interval = this.props.interval
    const { payload } = await getRecentTransactions(interval, 0)
    this.setState({ result: payload })
  }

  render() {
    return (
      <div>
        <Card as='a' onClick={this.props.click} style={{cursor: 'pointer'}}>
          <Card.Body>
            <Card.Title>{this.props.interval.replace(/-/g, ' ')} transactions:</Card.Title>
            <h3>{(this.state.result[0] === undefined) ? "processing..." : this.state.result[0].transactions}</h3>
          </Card.Body>
        </Card>
      </div>
    )
  }

}
export default RecentTransactions