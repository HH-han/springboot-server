import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del } from '../utils/request.js';

const { HOTEL } = API_CONFIG;

/**
 * 酒店管理接口
 */
export const hotelApi = {
  /**
   * 查询酒店列表
   * @param {Object} params 查询参数
   * @returns {Promise} 酒店列表
   */
  getList: (params) => get(HOTEL.BASE + HOTEL.ENDPOINTS.LIST, params),
  
  /**
   * 新增酒店
   * @param {Object} data 酒店数据
   * @returns {Promise} 创建结果
   */
  create: (data) => post(HOTEL.BASE + HOTEL.ENDPOINTS.CREATE, data),
  
  /**
   * 修改酒店
   * @param {string} id 酒店ID
   * @param {Object} data 酒店数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = HOTEL.BASE + HOTEL.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 删除酒店
   * @param {string} id 酒店ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = HOTEL.BASE + HOTEL.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  }
};