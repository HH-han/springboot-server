import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del, upload } from '../utils/request.js';

const { FOOD } = API_CONFIG;

/**
 * 美食管理接口
 */
export const foodApi = {
  /**
   * 分页查询美食
   * @param {Object} params 查询参数
   * @returns {Promise} 美食列表
   */
  getList: (params) => get(FOOD.BASE + FOOD.ENDPOINTS.LIST, params),
  
  /**
   * 搜索美食
   * @param {Object} params 搜索参数
   * @returns {Promise} 搜索结果
   */
  search: (params) => get(FOOD.BASE + FOOD.ENDPOINTS.SEARCH, params),
  
  /**
   * 根据ID查询美食
   * @param {string} id 美食ID
   * @returns {Promise} 美食详情
   */
  getDetail: (id) => {
    const url = FOOD.BASE + FOOD.ENDPOINTS.DETAIL.replace('{id}', id);
    return get(url);
  },
  
  /**
   * 新增美食
   * @param {Object} data 美食数据
   * @param {string} filePath 图片文件路径
   * @returns {Promise} 创建结果
   */
  create: (data, filePath) => {
    if (filePath) {
      return upload(FOOD.BASE + FOOD.ENDPOINTS.CREATE, filePath, data);
    }
    return post(FOOD.BASE + FOOD.ENDPOINTS.CREATE, data);
  },
  
  /**
   * 修改美食
   * @param {string} id 美食ID
   * @param {Object} data 美食数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = FOOD.BASE + FOOD.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 删除美食
   * @param {string} id 美食ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = FOOD.BASE + FOOD.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  }
};