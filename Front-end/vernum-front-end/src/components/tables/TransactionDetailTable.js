import React, { Component } from 'react'
import { Table } from 'react-bootstrap'
import { getRecentTransactions } from '../../api'

class TransactionDetailTable extends Component {

  constructor() {
    super()
    this.state = {
      result: []
    }
  }

  async componentDidMount() {
    const fetched = await getRecentTransactions(this.props.interval, "1")
    this.setState({ result: fetched.payload })
  }

  render() {
    return (
      <Table
        responsive
        stripped hover
        borderles={true}>
        <thead>
          <tr>
            {this.state.result.map(r =>
              <th>{Object.keys(r)}</th>)
            }
          </tr>
        </thead>
        <tbody>
          {this.state.result.map(row =>
            <tr>
              {Object.values(row).map(val =>
                <td>{val}</td>)}
            </tr>)}
        </tbody>
      </Table>
    )
  }

}

export default TransactionDetailTable