import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del, upload } from '../utils/request.js';

const { BLOG } = API_CONFIG;

/**
 * 旅游博客接口
 */
export const blogApi = {
  /**
   * 分页查询博客
   * @param {Object} params 查询参数
   * @returns {Promise} 博客列表
   */
  getList: (params) => get(BLOG.BASE + BLOG.ENDPOINTS.LIST, params),
  
  /**
   * 根据ID查询博客
   * @param {string} id 博客ID
   * @returns {Promise} 博客详情
   */
  getDetail: (id) => {
    const url = BLOG.BASE + BLOG.ENDPOINTS.DETAIL.replace('{id}', id);
    return get(url);
  },
  
  /**
   * 新增博客
   * @param {Object} data 博客数据
   * @param {string} filePath 封面图片路径
   * @returns {Promise} 创建结果
   */
  create: (data, filePath) => {
    if (filePath) {
      return upload(BLOG.BASE + BLOG.ENDPOINTS.CREATE, filePath, data);
    }
    return post(BLOG.BASE + BLOG.ENDPOINTS.CREATE, data);
  },
  
  /**
   * 更新博客
   * @param {string} id 博客ID
   * @param {Object} data 博客数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = BLOG.BASE + BLOG.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 删除博客
   * @param {string} id 博客ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = BLOG.BASE + BLOG.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  },
  
  /**
   * 点赞博客
   * @param {string} id 博客ID
   * @returns {Promise} 点赞结果
   */
  like: (id) => {
    const url = BLOG.BASE + BLOG.ENDPOINTS.LIKE.replace('{id}', id);
    return post(url);
  },
  
  /**
   * 收藏博客
   * @param {string} id 博客ID
   * @returns {Promise} 收藏结果
   */
  favorite: (id) => {
    const url = BLOG.BASE + BLOG.ENDPOINTS.FAVORITE.replace('{id}', id);
    return post(url);
  }
};