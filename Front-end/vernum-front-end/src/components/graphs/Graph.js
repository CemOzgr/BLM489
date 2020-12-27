import React,{Component} from 'react'
import {getBestSellers} from '../../api'

class Graph extends Component {
  constructor() {
    super()
    this.state = {
      result: {}
    }
  }

  async componentDidMount() {
    const fetched = await getBestSellers("ps4")
    this.setState({result: fetched})
  }

  render() {
    return(
      <div><h1>graph</h1></div>
    )
  }

}

export default Graph