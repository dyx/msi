import axios from 'axios'
import { ElMessage } from "element-plus";

const msgSet = new Set()
// https://axios-http.com/docs/instance
const instance = axios.create({
  baseURL: '/api',
  timeout: 30000
})

const err = error => {
  if (error.response) {
    const res = error.response.data
    const status = error.response.status
    // 未知异常
    if (status === 400 || status === 403 || status === 500) {
      msgSet.add(res.msg ? res.msg : '服务器发生错误')
      setTimeout(() => {
        msgSet.forEach(item => {
          ElMessage.error(item)
        })
        msgSet.clear()
      }, 500)
    }
    return Promise.reject(res)
  }
  return Promise.reject(error)
}

// 响应拦截器
instance.interceptors.response.use(response => {
  let res = response.data
  if (res.code && res.code !== 0) {
    return Promise.reject(res);
  }
  return res;
}, err)

export default instance
