import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del, upload } from '../utils/request.js';

const { SCENIC } = API_CONFIG;

/**
 * 景点管理接口
 */
export const scenicApi = {
  /**
   * 分页查询景点
   * @param {Object} params 查询参数
   * @returns {Promise} 景点列表
   */
  getList: (params) => get(SCENIC.BASE + SCENIC.ENDPOINTS.LIST, params),
  
  /**
   * 获取景点详情
   * @param {string} id 景点ID
   * @returns {Promise} 景点详情
   */
  getDetail: (id) => {
    const url = SCENIC.BASE + '/{id}'.replace('{id}', id);
    return get(url);
  },
  
  /**
   * 新增景点
   * @param {Object} data 景点数据
   * @param {string} filePath 图片文件路径
   * @returns {Promise} 创建结果
   */
  create: (data, filePath) => {
    if (filePath) {
      return upload(SCENIC.BASE + SCENIC.ENDPOINTS.CREATE, filePath, data);
    }
    return post(SCENIC.BASE + SCENIC.ENDPOINTS.CREATE, data);
  },
  
  /**
   * 修改景点
   * @param {string} id 景点ID
   * @param {Object} data 景点数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = SCENIC.BASE + SCENIC.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 删除景点
   * @param {string} id 景点ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = SCENIC.BASE + SCENIC.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  }
};