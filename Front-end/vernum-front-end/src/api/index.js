import axios from 'axios'
import qs from 'qs'

const isRequired = () => {throw new Error("parameter is required!")}

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
    const response = await axios.post("/login", body, config)

    return response
  } catch (error) {

  }

}

export const getBestSellers = async (platform, region) => {
  try {
    const { data } = await axios.get(`api/games/sales/${platform}/best-sellers`,
      { params: { region } })

    return data

  } catch (error) {

  }
}

export const getBestPublishers = async (platform) => {
  try {
    const { data } = await axios.get(`api/games/sales/${platform}/top-publishers`)

    return data

  } catch (error) {

  }
}

export const getYearSales = async (platform, genre) => {
  try {
    const { data } = await axios.get(`api/games/sales/${platform}/years`,
      { params: { genre } })

    return data

  } catch (error) {

  }
}

export const getPublisherRevenue = async (publisher) => {
  try {
    const { data } = await axios.get(`api/games/sales/all/top-publishers/${publisher}`)

    return data

  } catch (error) {

  }
}

export const getGenres = async (platform) => {
  try {
    const { data } = await axios.get(`api/games/market/${platform}/genres`)

    return data

  } catch (error) {

  }
}
/** city: {1-22} */
export const getCustomerGenderProfile = async (city) => {
  try {
    const { data } = await axios.get(`api/churn/customers/genders`,
      { params: { city } })

    return data

  } catch (error) {

  }
}

export const getTransactions = async (year) => {
  try {
    if(year === undefined) {
      const { data } = await axios.get(`api/churn/transactions/years`)
      return data
    }else {
      const { data } = await axios.get(`api/churn/transactions/years/${year}`)
      return data
    }
      
  } catch (error) {

  }
}

export const getRecentTransactions = async (interval, detalied=isRequired()) => {
  try {
    const { data } = await axios.get(`api/churn/transactions/recent`,
      {
        params: {
          interval,
          detalied
        }
      })

    return data

  } catch (error) {

  }
}

