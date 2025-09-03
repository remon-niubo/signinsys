import request from '@/utils/request'

// 查询签到列表
export function listSignin(query) {
  return request({
    url: '/system/signin/list',
    method: 'get',
    params: query
  })
}

// 获取详情
export function getSignin(id) {
  return request({
    url: '/system/signin/' + id,
    method: 'get'
  })
}

// 新增签到
export function addSignin(data) {
  return request({
    url: '/system/signin',
    method: 'post',
    data: data
  })
}

// 修改签到
export function updateSignin(data) {
  return request({
    url: '/system/signin',
    method: 'put',
    data: data
  })
}

// 删除签到
export function delSignin(id) {
  return request({
    url: '/system/signin/' + id,
    method: 'delete'
  })
}

// 用户签到
export function doSignin(id) {
  return request({
    url: '/system/signin/doSignin/' + id,
    method: 'post'
  })
}

// 查看签到结果
export function getResult(id) {
  return request({
    url: '/system/signin/result/' + id,
    method: 'get'
  })
}
