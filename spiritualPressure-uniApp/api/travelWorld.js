import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del } from '../utils/request.js';

const { TRAVEL_WORLD } = API_CONFIG;

/**
 * 旅游世界特性接口
 */
export const travelWorldApi = {
  /**
   * 分页查询特性
   * @param {Object} params 查询参数
   * @returns {Promise} 特性列表
   */
  getList: (params) => get(TRAVEL_WORLD.BASE + TRAVEL_WORLD.ENDPOINTS.LIST, params),
  
  /**
   * 新增特性
   * @param {Object} data 特性数据
   * @returns {Promise} 创建结果
   */
  create: (data) => post(TRAVEL_WORLD.BASE + TRAVEL_WORLD.ENDPOINTS.CREATE, data),
  
  /**
   * 修改特性
   * @param {string} id 特性ID
   * @param {Object} data 特性数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = TRAVEL_WORLD.BASE + TRAVEL_WORLD.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 删除特性
   * @param {string} id 特性ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = TRAVEL_WORLD.BASE + TRAVEL_WORLD.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  }
};