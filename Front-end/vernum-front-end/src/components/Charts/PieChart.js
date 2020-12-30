import React from 'react'
import ChartistGraph from 'react-chartist'

export const PieChart = props => {

  return (
    <ChartistGraph data={props.data} type="Pie" />
  )

}