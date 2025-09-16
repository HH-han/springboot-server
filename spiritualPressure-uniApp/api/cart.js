import { API_CONFIG } from '../utils/config.js';
import { post } from '../utils/request.js';

const { CART } = API_CONFIG;

/**
 * 购物车管理接口
 */
export const cartApi = {
  /**
   * 添加购物车
   * @param {Object} data 购物车数据
   * @returns {Promise} 添加结果
   */
  add: (data) => post(CART.BASE + CART.ENDPOINTS.ADD, data)
};