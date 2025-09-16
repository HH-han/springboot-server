import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUserStore = defineStore('user', () => {
  // 用户信息
  const userInfo = ref({
    id: null,
    username: '',
    avatar: '',
    level: '',
    realNameVerified: false,
    phone: '',
    email: ''
  })

  // 用户统计数据
  const userStats = ref({
    orderCount: 0,
    reviewCount: 0,
    collectionCount: 0,
    couponCount: 0
  })

  // 登录状态
  const isLoggedIn = ref(false)
  const token = ref('')

  // 获取用户信息
  const getUserInfo = async () => {
    try {
      // 这里调用API获取用户信息
      // 模拟数据
      const mockUserData = {
        id: 1,
        username: '旅行者',
        avatar: '/static/avatar/default.png',
        level: '黄金会员',
        realNameVerified: false,
        phone: '138****8888',
        email: 'user@example.com'
      }
      
      userInfo.value = mockUserData
      return mockUserData
    } catch (error) {
      console.error('获取用户信息失败:', error)
      throw error
    }
  }

  // 获取用户统计数据
  const getUserStats = async () => {
    try {
      // 这里调用API获取用户统计数据
      // 模拟数据
      const mockStats = {
        orderCount: 12,
        reviewCount: 8,
        collectionCount: 15,
        couponCount: 3
      }
      
      userStats.value = mockStats
      return mockStats
    } catch (error) {
      console.error('获取用户统计数据失败:', error)
      throw error
    }
  }

  // 登录
  const login = async (loginData) => {
    try {
      // 这里调用登录API
      // 模拟登录成功
      const mockResponse = {
        token: 'mock-jwt-token-123456',
        user: {
          id: 1,
          username: loginData.username || '旅行者',
          avatar: '/static/avatar/default.png',
          level: '黄金会员'
        }
      }
      
      token.value = mockResponse.token
      userInfo.value = mockResponse.user
      isLoggedIn.value = true
      
      // 存储token到本地存储
      uni.setStorageSync('token', mockResponse.token)
      
      return mockResponse
    } catch (error) {
      console.error('登录失败:', error)
      throw error
    }
  }

  // 退出登录
  const logout = async () => {
    try {
      // 这里调用退出登录API
      // 清除本地存储
      uni.removeStorageSync('token')
      
      // 重置状态
      token.value = ''
      isLoggedIn.value = false
      userInfo.value = {
        id: null,
        username: '',
        avatar: '',
        level: '',
        realNameVerified: false,
        phone: '',
        email: ''
      }
      
      return true
    } catch (error) {
      console.error('退出登录失败:', error)
      throw error
    }
  }

  // 更新用户信息
  const updateUserInfo = async (newInfo) => {
    try {
      // 这里调用API更新用户信息
      userInfo.value = { ...userInfo.value, ...newInfo }
      return userInfo.value
    } catch (error) {
      console.error('更新用户信息失败:', error)
      throw error
    }
  }

  // 检查登录状态
  const checkLoginStatus = () => {
    const storedToken = uni.getStorageSync('token')
    if (storedToken) {
      token.value = storedToken
      isLoggedIn.value = true
      // 自动获取用户信息
      getUserInfo()
      return true
    }
    return false
  }

  // 初始化
  const initialize = () => {
    checkLoginStatus()
  }

  return {
    userInfo,
    userStats,
    isLoggedIn,
    token,
    getUserInfo,
    getUserStats,
    login,
    logout,
    updateUserInfo,
    checkLoginStatus,
    initialize
  }
})