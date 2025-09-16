import { API_CONFIG } from '../utils/config.js';
import { get } from '../utils/request.js';

const { PROVINCE } = API_CONFIG;

/**
 * 省份管理接口
 */
export const provinceApi = {
  /**
   * 查询所有省份
   * @returns {Promise} 省份列表
   */
  getAll: () => get(PROVINCE.BASE + PROVINCE.ENDPOINTS.LIST),
  
  /**
   * 根据ID查询省份
   * @param {string} id 省份ID
   * @returns {Promise} 省份详情
   */
  getDetail: (id) => {
    const url = PROVINCE.BASE + PROVINCE.ENDPOINTS.DETAIL.replace('{id}', id);
    return get(url);
  }
};