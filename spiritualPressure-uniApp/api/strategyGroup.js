import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del, upload } from '../utils/request.js';

const { STRATEGY_GROUP } = API_CONFIG;

/**
 * 攻略组接口
 */
export const strategyGroupApi = {
  /**
   * 分页查询攻略组
   * @param {Object} params 查询参数
   * @returns {Promise} 攻略组列表
   */
  getList: (params) => get(STRATEGY_GROUP.BASE + STRATEGY_GROUP.ENDPOINTS.LIST, params),
  
  /**
   * 根据ID查询攻略组
   * @param {string} id 攻略组ID
   * @returns {Promise} 攻略组详情
   */
  getDetail: (id) => {
    const url = STRATEGY_GROUP.BASE + STRATEGY_GROUP.ENDPOINTS.DETAIL.replace('{id}', id);
    return get(url);
  },
  
  /**
   * 新增攻略组
   * @param {Object} data 攻略组数据
   * @param {string} filePath 图片文件路径
   * @returns {Promise} 创建结果
   */
  create: (data, filePath) => {
    if (文件路径) {
      return upload(STRATEGY_GROUP.BASE + STRATEGY_GROUP.ENDPOINTS.CREATE, filePath, data);
    }
    return post(STRATEGY_GROUP.BASE + STRATEGY_GROUP.ENDPOINTS.CREATE, data);
  },
  
  /**
   * 更新攻略组
   * @param {string} id 攻略组ID
   * @param {Object} data 攻略组数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = STRATEGY_GROUP.BASE + STRATEGY_GROUP.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 删除攻略组
   * @param {string} id 攻略组ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = STRATEGY_GROUP.BASE + STRATEGY_GROUP.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  },
  
  /**
   * 按标题搜索攻略组
   * @param {Object} params 搜索参数
   * @returns {Promise} 搜索结果
   */
  searchByTitle: (params) => get(STRATEGY_GROUP.BASE + STRATEGY_GROUP.ENDPOINTS.SEARCH_TITLE, params)
};