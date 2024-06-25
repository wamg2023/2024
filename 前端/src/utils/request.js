import axios from 'axios'

const baseURL = '/api'

const request = axios.create({
    baseURL,
    timeout: 10000
})

request.interceptors.request.use(
    (config) => {
        return config
    },
    (err) => Promise.reject(err)
)

request.interceptors.response.use(
    (res) => {
        return res
    },
    (err) => {
        return Promise.reject(err)
    }
)

export default request