import { API_CONFIG } from './config.js';
import { post } from './request.js';

const { AUTH } = API_CONFIG;

/**
 * 认证管理接口
 */
export const authApi = {
  /**
   * 发送验证码
   * @param {Object} data 邮箱数据
   * @returns {Promise} 发送结果
   */
  sendCode: (data) => post(AUTH.BASE + AUTH.ENDPOINTS.SEND_CODE, data),
  
  /**
   * 邮箱验证码登录
   * @param {Object} data 登录数据
   * @returns {Promise} 登录结果
   */
  emailLogin: (data) => post(AUTH.BASE + AUTH.ENDPOINTS.EMAIL_LOGIN, data)
};