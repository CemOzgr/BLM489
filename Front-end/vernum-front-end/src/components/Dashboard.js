import React,{useState} from "react"
import { Col, Container, Row } from "react-bootstrap"
import GenderGraph from './graphs/GenderGraph'
import RecentTransactions from "./graphs/RecentTransactions"
import TransactionGraph from "./graphs/TransactionsGraph"
import {TransactionsDetailModal} from "./modals/TransactionsDetailModal"

const Dashboard = props => {

  const [modalDate,setModalDate] = useState("")
  const [showDetails,setShowDetails] = useState(false)

  const handleClick= modalDate => {
    setModalDate(modalDate)
    setShowDetails(true)
  }

  return (
    <div className="content">
      <Container fluid>
        <TransactionsDetailModal 
          modalDate={modalDate}
          show={showDetails}
          onHide={() => setShowDetails(false)}/> 
        <Row>
          <Col md={4}>
            <RecentTransactions interval="today" click={() => handleClick("today")}/>
          </Col>
          <Col md={4}>
            <RecentTransactions interval="this-week" click={() => handleClick("this-week")}/>
          </Col>
          <Col md={4}>
            <RecentTransactions interval="this-month" click={() => handleClick("this-month")}/>
          </Col>
        </Row>

        <Row>
          <Col md={4}>
            <GenderGraph />
          </Col>
          <Col md={8}>
            <TransactionGraph />
          </Col>
        </Row>
      </Container>

    </div>
  )
}

export default Dashboard