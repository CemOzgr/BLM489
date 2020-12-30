import React from 'react'
import ChartistGraph from 'react-chartist'

export const LineChart = props => {

  return (
    <ChartistGraph data={props.data} type="Line"/>
  )

}