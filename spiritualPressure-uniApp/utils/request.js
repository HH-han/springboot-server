import { BASE_URL } from './config.js';

// 请求拦截器
const requestInterceptor = (config) => {
  // 添加认证token
  const token = uni.getStorageSync('token');
  if (token) {
    config.header = config.header || {};
    config.header['Authorization'] = `Bearer ${token}`;
  }
  
  // 添加基础URL
  if (!config.url.startsWith('http')) {
    config.url = BASE_URL + config.url;
  }
  
  return config;
};

// 响应拦截器
const responseInterceptor = (response) => {
  const { statusCode, data } = response;
  
  if (statusCode === 200) {
    return data;
  } else if (statusCode === 401) {
    // token过期，跳转到登录页
    uni.removeStorageSync('token');
    uni.showToast({
      title: '登录已过期，请重新登录',
      icon: 'none'
    });
    uni.navigateTo({
      url: '/pages/login/login'
    });
    return Promise.reject(new Error('登录已过期'));
  } else {
    return Promise.reject(new Error(`请求失败: ${statusCode}`));
  }
};

// 错误拦截器
const errorInterceptor = (error) => {
  console.error('请求错误:', error);
  uni.showToast({
    title: '网络请求失败',
    icon: 'none'
  });
  return Promise.reject(error);
};

// 基础请求方法
export const request = (options) => {
  const config = requestInterceptor(options);
  
  return new Promise((resolve, reject) => {
    uni.request({
      ...config,
      success: (res) => {
        try {
          const result = responseInterceptor(res);
          resolve(result);
        } catch (error) {
          reject(error);
        }
      },
      fail: (error) => {
        const handledError = errorInterceptor(error);
        reject(handledError);
      }
    });
  });
};

// GET请求
export const get = (url, data = {}, config = {}) => {
  return request({
    url,
    data,
    method: 'GET',
    ...config
  });
};

// POST请求
export const post = (url, data = {}, config = {}) => {
  return request({
    url,
    data,
    method: 'POST',
    header: {
      'Content-Type': 'application/json',
      ...config.header
    },
    ...config
  });
};

// PUT请求
export const put = (url, data = {}, config = {}) => {
  return request({
    url,
    data,
    method: 'PUT',
    header: {
      'Content-Type': 'application/json',
      ...config.header
    },
    ...config
  });
};

// DELETE请求
export const del = (url, data = {}, config = {}) => {
  return request({
    url,
    data,
    method: 'DELETE',
    ...config
  });
};

// 文件上传
export const upload = (url, filePath, formData = {}, config = {}) => {
  return new Promise((resolve, reject) => {
    const uploadConfig = requestInterceptor({
      url,
      method: 'POST',
      ...config
    });
    
    uni.uploadFile({
      url: uploadConfig.url,
      filePath,
      name: 'file',
      formData,
      header: uploadConfig.header,
      success: (res) => {
        try {
          const result = responseInterceptor({
            statusCode: res.statusCode,
            data: res.data ? JSON.parse(res.data) : res.data
          });
          resolve(result);
        } catch (error) {
          reject(error);
        }
      },
      fail: (error) => {
        const handledError = errorInterceptor(error);
        reject(handledError);
      }
    });
  });
};