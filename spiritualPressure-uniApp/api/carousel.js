import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del, upload } from '../utils/request.js';

const { CAROUSEL } = API_CONFIG;

/**
 * 轮播图管理接口
 */
export const carouselApi = {
  /**
   * 分页查询轮播图
   * @param {Object} params 查询参数
   * @returns {Promise} 轮播图列表
   */
  getList: (params) => get(CAROUSEL.BASE + CAROUSEL.ENDPOINTS.LIST, params),
  
  /**
   * 新增轮播图
   * @param {Object} data 轮播图数据
   * @param {string} filePath 图片文件路径
   * @returns {Promise} 创建结果
   */
  create: (data, filePath) => {
    if (filePath) {
      return upload(CAROUSEL.BASE + CAROUSEL.ENDPOINTS.CREATE, filePath, data);
    }
    return post(CAROUSEL.BASE + CAROUSEL.ENDPOINTS.CREATE, data);
  },
  
  /**
   * 修改轮播图
   * @param {string} id 轮播图ID
   * @param {Object} data 轮播图数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = CAROUSEL.BASE + CAROUSEL.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 删除轮播图
   * @param {string} id 轮播图ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = CAROUSEL.BASE + CAROUSEL.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  }
};