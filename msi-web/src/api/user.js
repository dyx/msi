import request from '@/utils/request'

export const getUserPage = params => {
  return request.get('/sys/user/page', { params })
}

export const getUser = id => {
  return request.get('/sys/user/' + id)
}

export const addUser = data => {
  return request.post('/sys/user', data)
}

export const updateUser = (id, data) => {
  return request.put('/sys/user/' + id, data)
}

export const removeUser = id => {
  return request.delete('/sys/user/' + id)
}
