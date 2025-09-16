import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del } from '../utils/request.js';

const { RECOMMEND } = API_CONFIG;

/**
 * 旅游推荐接口
 */
export const recommendApi = {
  /**
   * 查询推荐列表
   * @param {Object} params 查询参数
   * @returns {Promise} 推荐列表
   */
  getList: (params) => get(RECOMMEND.BASE + RECOMMEND.ENDPOINTS.LIST, params),
  
  /**
   * 新增推荐
   * @param {Object} data 推荐数据
   * @returns {Promise} 创建结果
   */
  create: (data) => post(RECOMMEND.BASE + RECOMMEND.ENDPOINTS.CREATE, data),
  
  /**
   * 修改推荐
   * @param {string} id 推荐ID
   * @param {Object} data 推荐数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = RECOMMEND.BASE + RECOMMEND.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 删除推荐
   * @param {string} id 推荐ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = RECOMMEND.BASE + RECOMMEND.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  }
};