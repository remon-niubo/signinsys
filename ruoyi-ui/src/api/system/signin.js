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
  console.log("调用用户签到接口，签到ID:", id, "类型:", typeof id);
  if (id === undefined || id === null) {
    return Promise.reject(new Error("签到ID不能为空"));
  }
  
  // 确保ID是数字类型
  const numericId = Number(id);
  if (isNaN(numericId)) {
    return Promise.reject(new Error("签到ID必须是数字"));
  }
  
  return request({
    url: '/system/signin/doSignin/' + numericId,
    method: 'post'
  }).catch(error => {
    console.error("用户签到接口调用失败:", error);
    throw error;
  });
}

// 查看签到结果
export function getResult(id) {
  console.log("调用查看签到结果接口，签到ID:", id, "类型:", typeof id);
  if (id === undefined || id === null) {
    return Promise.reject(new Error("签到ID不能为空"));
  }
  
  // 确保ID是数字类型
  const numericId = Number(id);
  if (isNaN(numericId)) {
    return Promise.reject(new Error("签到ID必须是数字"));
  }
  
  return request({
    url: '/system/signin/result/' + numericId,
    method: 'get'
  }).catch(error => {
    console.error("查看签到结果接口调用失败:", error);
    throw error;
  });
}
