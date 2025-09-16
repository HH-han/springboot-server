import { API_CONFIG } from '../utils/config.js';
import { get, post, put, del, upload } from '../utils/request.js';

const { USER } = API_CONFIG;

/**
 * 用户管理接口
 */
export const userApi = {
  /**
   * 获取用户信息
   * @returns {Promise} 用户信息
   */
  getInfo: () => get(USER.BASE + USER.ENDPOINTS.INFO),
  
  /**
   * 用户登录
   * @param {Object} data 登录数据
   * @returns {Promise} 登录结果
   */
  login: (data) => post(USER.BASE + USER.ENDPOINTS.LOGIN, data),
  
  /**
   * 管理员登录
   * @param {Object} data 管理员登录数据
   * @returns {Promise} 登录结果
   */
  adminLogin: (data) => post(USER.BASE + USER.ENDPOINTS.ADMIN_LOGIN, data),
  
  /**
   * 上传用户头像
   * @param {string} username 用户名
   * @param {string} filePath 文件路径
   * @returns {Promise} 上传结果
   */
  uploadAvatar: (username, filePath) => {
    const url = USER.BASE + USER.ENDPOINTS.AVATAR.replace('{username}', username);
    return upload(url, filePath);
  },
  
  /**
   * 更新用户信息
   * @param {Object} data 用户数据
   * @returns {Promise} 更新结果
   */
  updateUser: (data) => put(USER.BASE + USER.ENDPOINTS.UPDATE, data),
  
  /**
   * 修改用户权限
   * @param {Object} data 权限数据
   * @returns {Promise} 修改结果
   */
  updateRole: (data) => put(USER.BASE + USER.ENDPOINTS.UPDATE_ROLE, data),
  
  /**
   * 修改用户状态
   * @param {Object} data 状态数据
   * @returns {Promise} 修改结果
   */
  updateStatus: (data) => put(USER.BASE + USER.ENDPOINTS.UPDATE_STATUS, data),
  
  /**
   * 搜索用户
   * @param {Object} params 搜索参数
   * @returns {Promise} 搜索结果
   */
  searchUser: (params) => get(USER.BASE + USER.ENDPOINTS.SEARCH, params),
  
  /**
   * 导出用户列表
   * @returns {Promise} 导出结果
   */
  exportUsers: () => get(USER.BASE + USER.ENDPOINTS.EXPORT),
  
  /**
   * 导入用户数据
   * @param {string} filePath Excel文件路径
   * @returns {Promise} 导入结果
   */
  importUsers: (filePath) => {
    return upload(USER.BASE + USER.ENDPOINTS.IMPORT, filePath);
  },
  
  /**
   * 用户注册
   * @param {Object} data 注册数据
   * @returns {Promise} 注册结果
   */
  register: (data) => post(USER.BASE + USER.ENDPOINTS.REGISTER, data)
};