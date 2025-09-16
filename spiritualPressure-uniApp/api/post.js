import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del } from '../utils/request.js';

const { POST } = API_CONFIG;

/**
 * 帖子管理接口
 */
export const postApi = {
  /**
   * 分页查询帖子
   * @param {Object} params 查询参数
   * @returns {Promise} 帖子列表
   */
  getList: (params) => get(POST.BASE + POST.ENDPOINTS.LIST, params),
  
  /**
   * 根据ID查询帖子
   * @param {string} id 帖子ID
   * @returns {Promise} 帖子详情
   */
  getDetail: (id) => {
    const url = POST.BASE + POST.ENDPOINTS.DETAIL.replace('{id}', id);
    return get(url);
  },
  
  /**
   * 新增帖子
   * @param {Object} data 帖子数据
   * @returns {Promise} 创建结果
   */
  create: (data) => post(POST.BASE + POST.ENDPOINTS.CREATE, data),
  
  /**
   * 更新帖子
   * @param {string} id 帖子ID
   * @param {Object} data 帖子数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = POST.BASE + POST.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 删除帖子
   * @param {string} id 帖子ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = POST.BASE + POST.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  },
  
  /**
   * 按地点搜索帖子
   * @param {Object} params 搜索参数
   * @returns {Promise} 搜索结果
   */
  searchByDestination: (params) => get(POST.BASE + POST.ENDPOINTS.SEARCH_DESTINATION, params),
  
  /**
   * 按标签搜索帖子
   * @param {Object} params 搜索参数
   * @returns {Promise} 搜索结果
   */
  searchByTag: (params) => get(POST.BASE + POST.ENDPOINTS.SEARCH_TAG, params)
};