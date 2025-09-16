import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del } from '../utils/request.js';

const { SAFETY_TIPS } = API_CONFIG;

/**
 * 安全提示接口
 */
export const safetyTipsApi = {
  /**
   * 分页查询安全提示
   * @param {Object} params 查询参数
   * @returns {Promise} 安全提示列表
   */
  getList: (params) => get(SAFETY_TIPS.BASE + SAFETY_TIPS.ENDPOINTS.LIST, params),
  
  /**
   * 根据ID查询安全提示
   * @param {string} id 安全提示ID
   * @returns {Promise} 安全提示详情
   */
  getDetail: (id) => {
    const url = SAFETY_TIPS.BASE + SAFETY_TIPS.ENDPOINTS.DETAIL.replace('{id}', id);
    return get(url);
  },
  
  /**
   * 统计安全提示总数
   * @returns {Promise} 总数统计
   */
  getCount: () => get(SAFETY_TIPS.BASE + SAFETY_TIPS.ENDPOINTS.COUNT),
  
  /**
   * 新增安全提示
   * @param {Object} data 安全提示数据
   * @returns {Promise} 创建结果
   */
  create: (data) => post(SAFETY_TIPS.BASE + SAFETY_TIPS.ENDPOINTS.CREATE, data),
  
  /**
   * 批量新增安全提示
   * @param {Array} data 安全提示数据数组
   * @returns {Promise} 批量创建结果
   */
  batchCreate: (data) => post(SAFETY_TIPS.BASE + SAFETY_TIPS.ENDPOINTS.BATCH_CREATE, data),
  
  /**
   * 条件查询安全提示
   * @param {Object} data 查询条件
   * @returns {Promise} 查询结果
   */
  conditionSearch: (data) => post(SAFETY_TIPS.BASE + SAFETY_TIPS.ENDPOINTS.CONDITION_SEARCH, data),
  
  /**
   * 条件统计安全提示
   * @param {Object} data 统计条件
   * @returns {Promise} 统计结果
   */
  conditionCount: (data) => post(SAFETY_TIPS.BASE + SAFETY_TIPS.ENDPOINTS.CONDITION_COUNT, data),
  
  /**
   * 更新安全提示
   * @param {string} id 安全提示ID
   * @param {Object} data 安全提示数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = SAFETY_TIPS.BASE + SAFETY_TIPS.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 批量更新安全提示
   * @param {Array} data 安全提示数据数组
   * @returns {Promise} 批量更新结果
   */
  batchUpdate: (data) => put(SAFETY_TIPS.BASE + SAFETY_TIPS.ENDPOINTS.BATCH_UPDATE, data),
  
  /**
   * 删除安全提示
   * @param {string} id 安全提示ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = SAFETY_TIPS.BASE + SAFETY_TIPS.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  },
  
  /**
   * 批量删除安全提示
   * @param {Array} ids 安全提示ID数组
   * @returns {Promise} 批量删除结果
   */
  batchDelete: (ids) => del(SAFETY_TIPS.BASE + SAFETY_TIPS.ENDPOINTS.BATCH_DELETE, { ids })
};