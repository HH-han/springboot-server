import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del } from '../utils/request.js';

const { NEWS } = API_CONFIG;

/**
 * 旅游新闻接口
 */
export const newsApi = {
  /**
   * 分页查询新闻
   * @param {Object} params 查询参数
   * @returns {Promise} 新闻列表
   */
  getList: (params) => get(NEWS.BASE + NEWS.ENDPOINTS.LIST, params),
  
  /**
   * 根据ID查询新闻
   * @param {string} id 新闻ID
   * @returns {Promise} 新闻详情
   */
  getDetail: (id) => {
    const url = NEWS.BASE + NEWS.ENDPOINTS.DETAIL.replace('{id}', id);
    return get(url);
  },
  
  /**
   * 新增新闻
   * @param {Object} data 新闻数据
   * @returns {Promise} 创建结果
   */
  create: (data) => post(NEWS.BASE + NEWS.ENDPOINTS.CREATE, data),
  
  /**
   * 更新新闻
   * @param {string} id 新闻ID
   * @param {Object} data 新闻数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = NEWS.BASE + NEWS.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 删除新闻
   * @param {string} id 新闻ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = NEWS.BASE + NEWS.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  },
  
  /**
   * 点赞新闻
   * @returns {Promise} 点赞结果
   */
  like: () => post(NEWS.BASE + NEWS.ENDPOINTS.LIKE),
  
  /**
   * 收藏新闻
   * @returns {Promise} 收藏结果
   */
  collect: () => post(NEWS.BASE + NEWS.ENDPOINTS.COLLECT)
};