import { API_CONFIG } from '../utils/config.js';
import { get, post, put } from '../utils/request.js';

const { PAYMENT } = API_CONFIG;

/**
 * 支付管理接口
 */
export const paymentApi = {
  /**
   * 查询支付列表
   * @param {Object} params 查询参数
   * @returns {Promise} 支付列表
   */
  getList: (params) => get(PAYMENT.BASE + PAYMENT.ENDPOINTS.LIST, params),
  
  /**
   * 创建支付订单
   * @param {Object} data 支付数据
   * @returns {Promise} 创建结果
   */
  create: (data) => post(PAYMENT.BASE + PAYMENT.ENDPOINTS.CREATE, data),
  
  /**
   * 更新支付状态
   * @param {string} id 支付ID
   * @param {Object} data 支付数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = PAYMENT.BASE + PAYMENT.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  }
};