import axios from 'axios'
import qs from 'qs'

//const isRequired = () => {throw new Error("parameter is required!")}
const BASEURL = "localhost:8080/api"

export const login = async (username, password) => {

  const body = qs.stringify({
    username: username,
    password: password
  })

  const config = {
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    }
  }
  try {
    const response = await axios.post("http://localhost:8080/login", body, config)
    return response
  }catch(error) {

  }
  

}

export const getBestSellers = async (platform, region) => {
  try {
    const { data } = await axios.get(`http://${BASEURL}/games/sales/${platform}/best-sellers`)
    console.log(data)
    return data

  } catch (error) {

  }
}