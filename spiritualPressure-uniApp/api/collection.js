import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del } from '../utils/request.js';

const { COLLECTION } = API_CONFIG;

/**
 * 收藏管理接口
 */
export const collectionApi = {
  /**
   * 查询所有收藏
   * @returns {Promise} 收藏列表
   */
  getAll: () => get(COLLECTION.BASE + COLLECTION.ENDPOINTS.LIST),
  
  /**
   * 根据ID查询收藏
   * @param {string} id 收藏ID
   * @returns {Promise} 收藏详情
   */
  getDetail: (id) => {
    const url = COLLECTION.BASE + COLLECTION.ENDPOINTS.DETAIL.replace('{id}', id);
    return get(url);
  },
  
  /**
   * 按用户名查询收藏
   * @param {string} username 用户名
   * @returns {Promise} 用户收藏列表
   */
  getByUser: (username) => {
    const url = COLLECTION.BASE + COLLECTION.ENDPOINTS.USER.replace('{username}', username);
    return get(url);
  },
  
  /**
   * 分页查询收藏
   * @param {Object} params 分页参数
   * @returns {Promise} 分页收藏列表
   */
  getPage: (params) => get(COLLECTION.BASE + COLLECTION.ENDPOINTS.PAGE, params),
  
  /**
   * 查询收藏总数
   * @returns {Promise} 收藏总数
   */
  getCount: () => get(COLLECTION.BASE + COLLECTION.ENDPOINTS.COUNT),
  
  /**
   * 新增收藏
   * @param {Object} data 收藏数据
   * @returns {Promise} 创建结果
   */
  create: (data) => post(COLLECTION.BASE + COLLECTION.ENDPOINTS.CREATE, data),
  
  /**
   * 修改收藏
   * @param {string} id 收藏ID
   * @param {Object} data 收藏数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = COLLECTION.BASE + COLLECTION.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 删除收藏
   * @param {string} id 收藏ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = COLLECTION.BASE + COLLECTION.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  },
  
  /**
   * 高级搜索收藏
   * @param {Object} data 搜索条件
   * @returns {Promise} 搜索结果
   */
  advancedSearch: (data) => post(COLLECTION.BASE + COLLECTION.ENDPOINTS.ADVANCED_SEARCH, data)
};