import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del, upload } from '../utils/request.js';

const { TRAVEL_CARD } = API_CONFIG;

/**
 * 旅游卡管理接口
 */
export const travelCardApi = {
  /**
   * 获取旅游卡列表
   * @param {Object} params 查询参数
   * @returns {Promise} 旅游卡列表
   */
  getList: (params) => get(TRAVEL_CARD.BASE + TRAVEL_CARD.ENDPOINTS.LIST, params),
  
  /**
   * 获取单个旅游卡
   * @param {string} id 旅游卡ID
   * @returns {Promise} 旅游卡详情
   */
  getDetail: (id) => {
    const url = TRAVEL_CARD.BASE + TRAVEL_CARD.ENDPOINTS.DETAIL.replace('{id}', id);
    return get(url);
  },
  
  /**
   * 新增旅游卡
   * @param {Object} data 旅游卡数据
   * @param {string} filePath 图片文件路径
   * @returns {Promise} 创建结果
   */
  create: (data, filePath) => {
    if (filePath) {
      return upload(TRAVEL_CARD.BASE + TRAVEL_CARD.ENDPOINTS.CREATE, filePath, data);
    }
    return post(TRAVEL_CARD.BASE + TRAVEL_CARD.ENDPOINTS.CREATE, data);
  },
  
  /**
   * 更新旅游卡
   * @param {string} id 旅游卡ID
   * @param {Object} data 旅游卡数据
   * @returns {Promise} 更新结果
   */
  update: (id, data) => {
    const url = TRAVEL_CARD.BASE + TRAVEL_CARD.ENDPOINTS.UPDATE.replace('{id}', id);
    return put(url, data);
  },
  
  /**
   * 删除旅游卡
   * @param {string} id 旅游卡ID
   * @returns {Promise} 删除结果
   */
  delete: (id) => {
    const url = TRAVEL_CARD.BASE + TRAVEL_CARD.ENDPOINTS.DELETE.replace('{id}', id);
    return del(url);
  },
  
  /**
   * 按名称搜索
   * @param {Object} params 搜索参数
   * @returns {Promise} 搜索结果
   */
  searchByName: (params) => get(TRAVEL_CARD.BASE + TRAVEL_CARD.ENDPOINTS.SEARCH_NAME, params),
  
  /**
   * 按关键词搜索
   * @param {Object} params 搜索参数
   * @returns {Promise} 搜索结果
   */
  searchByKeyword: (params) => get(TRAVEL_CARD.BASE + TRAVEL_CARD.ENDPOINTS.SEARCH_KEYWORD, params),
  
  /**
   * 按分类搜索
   * @param {Object} params 搜索参数
   * @returns {Promise} 搜索结果
   */
  searchByCategory: (params) => get(TRAVEL_CARD.BASE + TRAVEL_CARD.ENDPOINTS.SEARCH_CATEGORY, params),
  
  /**
   * 按徽章搜索
   * @param {Object} params 搜索参数
   * @returns {Promise} 搜索结果
   */
  searchByBadge: (params) => get(TRAVEL_CARD.BASE + TRAVEL_CARD.ENDPOINTS.SEARCH_BADGE, params)
};