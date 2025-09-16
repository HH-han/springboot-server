import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del, upload } from '../utils/request.js';

const { ORDER } = API_CONFIG;

/**
 * 订单管理接口
 */
export const orderApi = {
  /**
   * 分页查询订单
   * @param {Object} params 查询参数
   * @returns {Promise} 订单列表
   */
  getList: (params) => get(ORDER.BASE + ORDER.ENDPOINTS.LIST, params),
  
  /**
   * 根据ID查询订单
   * @param {string} id 订单ID
   * @returns {Promise} 订单详情
   */
  getDetail: (id) => {
    const url = ORDER.BASE + ORDER.ENDPOINTS.DETAIL.replace('{id}', id);
    return get(url);
  },
  
  /**
   * 新增订单
   * @param {Object} data 订单数据
   * @param {string} filePath 图片文件路径
   * @returns {Promise} 创建结果
   */
  create: (data, filePath) => {
    if (filePath) {
      return upload(ORDER.BASE + ORDER.ENDPOINTS.CREATE, filePath, data);
    }
    return post(ORDER.BASE + ORDER.ENDPOINTS.CREATE, data);
  },
  
  /**
   * 更新订单
   * @param {string} id 订单ID
   * @param {Object} data 订单数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = ORDER.BASE + ORDER.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 删除订单
   * @param {string} id 订单ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = ORDER.BASE + ORDER.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  }
};