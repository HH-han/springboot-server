import { API_CONFIG } from '../utils/config.js';
import { get } from '../utils/request.js';

const { MONITOR } = API_CONFIG;

/**
 * 系统监控接口
 */
export const monitorApi = {
  /**
   * 获取服务器监控数据
   * @returns {Promise} 监控数据
   */
  getServerMonitor: () => get(MONITOR.BASE + MONITOR.ENDPOINTS.SERVER_MONITOR)
};