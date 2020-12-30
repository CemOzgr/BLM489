import React from 'react'
import { Container, Row, Col } from 'react-bootstrap'
import GenreNumbersGraph from './graphs/GenreNumbersGraph'
import PublisherRevenueGraph from './graphs/PublisherRevenueGraph'
import BestSellersTable from './tables/BestSellersTable'

export const GameComponent = () => {

  return (
    <div className="content">
      <Container fluid >
        <Row>
          <Col md={6}>
            <GenreNumbersGraph />
          </Col>
          <Col md={6}>
            <PublisherRevenueGraph />
          </Col>
        </Row>
        <Row>
          <Col>
            <BestSellersTable />
          </Col>
        </Row>
      </Container>
    </div>
  )

}