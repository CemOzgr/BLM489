import React from 'react'
import { Modal, Container} from 'react-bootstrap'
import GenderGraph from '../graphs/GenderGraph'

export const TransactionsDetailModal = props => {

  return (
    <Modal
      {...props}
      size="lg"
      centered>
      <Modal.Header closeButton>
        <Modal.Title>Details</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Container fluid>
            <GenderGraph />
        </Container>
      </Modal.Body>

    </Modal>
  )

}
