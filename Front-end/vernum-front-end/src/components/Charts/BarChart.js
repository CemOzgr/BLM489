import React from 'react'
import ChartistGraph from 'react-chartist'

export const BarChart = props => {

  return (
    <div>
      <ChartistGraph data={props.data} type="Bar" />
    </div>
  )

}