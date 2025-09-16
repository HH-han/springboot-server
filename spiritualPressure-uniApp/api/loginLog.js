import { API_CONFIG } from '../utils/config.js';
import { get } from '../utils/request.js';

const { LOGIN_LOG } = API_CONFIG;

/**
 * 登录日志接口
 */
export const loginLogApi = {
  /**
   * 查询登录日志列表
   * @param {Object} params 查询参数
   * @returns {Promise} 登录日志列表
   */
  getList: (params) => get(LOGIN_LOG.BASE + LOGIN_LOG.ENDPOINTS.LIST, params)
};