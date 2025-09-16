import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del } from '../utils/request.js';

const { DESTINATION } = API_CONFIG;

/**
 * 目的地管理接口
 */
export const destinationApi = {
  /**
   * 查询目的地列表
   * @returns {Promise} 目的地列表
   */
  getList: () => get(DESTINATION.BASE + DESTINATION.ENDPOINTS.LIST),
  
  /**
   * 新增目的地
   * @param {Object} data 目的地数据
   * @returns {Promise} 创建结果
   */
  create: (data) => post(DESTINATION.BASE + DESTINATION.ENDPOINTS.CREATE, data),
  
  /**
   * 修改目的地
   * @param {string} id 目的地ID
   * @param {Object} data 目的地数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = DESTINATION.BASE + DESTINATION.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 删除目的地
   * @param {string} id 目的地ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = DESTINATION.BASE + DESTINATION.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  }
};