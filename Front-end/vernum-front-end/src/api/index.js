import axios from 'axios'

//const isRequired = () => {throw new Error("parameter is required!")}
const BASEURL = "localhost:8080/api"

export const getBestSellers = async (platform, region) => {
  try {
    const {data} = await axios.get(`http://${BASEURL}/games/sales/${platform}/best-sellers`)                     
    return data
    
  } catch (error) {
    
  }
}