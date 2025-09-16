import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del } from '../utils/request.js';

const { NOTE } = API_CONFIG;

/**
 * 旅游笔记接口
 */
export const noteApi = {
  /**
   * 根据ID查询笔记
   * @param {string} id 笔记ID
   * @returns {Promise} 笔记详情
   */
  getDetail: (id) => {
    const url = NOTE.BASE + NOTE.ENDPOINTS.DETAIL.replace('{id}', id);
    return get(url);
  },
  
  /**
   * 分页查询笔记
   * @param {Object} params 查询参数
   * @returns {Promise} 笔记列表
   */
  getList: (params) => get(NOTE.BASE + NOTE.ENDPOINTS.LIST, params),
  
  /**
   * 新增笔记
   * @param {Object} data 笔记数据
   * @returns {Promise} 创建结果
   */
  create: (data) => post(NOTE.BASE + NOTE.ENDPOINTS.CREATE, data),
  
  /**
   * 更新笔记
   * @param {string} id 笔记ID
   * @param {Object} data 笔记数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = NOTE.BASE + NOTE.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 删除笔记
   * @param {string} id 笔记ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = NOTE.BASE + NOTE.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  },
  
  /**
   * 搜索笔记
   * @param {Object} params 搜索参数
   * @returns {Promise} 搜索结果
   */
  search: (params) => get(NOTE.BASE + NOTE.ENDPOINTS.SEARCH, params)
};