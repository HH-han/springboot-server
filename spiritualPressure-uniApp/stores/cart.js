import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useCartStore = defineStore('cart', () => {
  // 购物车商品列表
  const cartItems = ref([])

  // 购物车总数量
  const totalCount = computed(() => {
    return cartItems.value.reduce((total, item) => total + item.quantity, 0)
  })

  // 购物车总金额
  const totalAmount = computed(() => {
    return cartItems.value.reduce((total, item) => total + (item.price * item.quantity), 0)
  })

  // 添加到购物车
  const addToCart = (product) => {
    const existingItem = cartItems.value.find(item => item.id === product.id)
    
    if (existingItem) {
      // 如果商品已存在，增加数量
      existingItem.quantity += product.quantity || 1
    } else {
      // 如果商品不存在，添加到购物车
      cartItems.value.push({
        ...product,
        quantity: product.quantity || 1
      })
    }
    
    // 保存到本地存储
    saveCartToStorage()
  }

  // 从购物车移除
  const removeFromCart = (productId) => {
    const index = cartItems.value.findIndex(item => item.id === productId)
    if (index !== -1) {
      cartItems.value.splice(index, 1)
      saveCartToStorage()
    }
  }

  // 更新商品数量
  const updateQuantity = (productId, quantity) => {
    const item = cartItems.value.find(item => item.id === productId)
    if (item) {
      if (quantity <= 0) {
        removeFromCart(productId)
      } else {
        item.quantity = quantity
        saveCartToStorage()
      }
    }
  }

  // 清空购物车
  const clearCart = () => {
    cartItems.value = []
    uni.removeStorageSync('cart')
  }

  // 保存购物车到本地存储
  const saveCartToStorage = () => {
    uni.setStorageSync('cart', JSON.stringify(cartItems.value))
  }

  // 从本地存储加载购物车
  const loadCartFromStorage = () => {
    const storedCart = uni.getStorageSync('cart')
    if (storedCart) {
      try {
        cartItems.value = JSON.parse(storedCart)
      } catch (error) {
        console.error('解析购物车数据失败:', error)
        cartItems.value = []
      }
    }
  }

  // 获取购物车商品数量
  const getItemQuantity = (productId) => {
    const item = cartItems.value.find(item => item.id === productId)
    return item ? item.quantity : 0
  }

  // 检查商品是否在购物车中
  const isInCart = (productId) => {
    return cartItems.value.some(item => item.id === productId)
  }

  // 初始化购物车
  const initialize = () => {
    loadCartFromStorage()
  }

  return {
    cartItems,
    totalCount,
    totalAmount,
    addToCart,
    removeFromCart,
    updateQuantity,
    clearCart,
    getItemQuantity,
    isInCart,
    initialize
  }
})