// API基础配置
export const BASE_URL = 'http://localhost:2025';

export const API_CONFIG = {
  // 用户管理接口
  USER: {
    BASE: '/api/public/user',
    ENDPOINTS: {
      INFO: '/info',
      LOGIN: '/login',
      ADMIN_LOGIN: '/adminlogin',
      AVATAR: '/avatar/{username}',
      UPDATE: '/update',
      UPDATE_ROLE: '/updateRole',
      UPDATE_STATUS: '/updateStatus',
      SEARCH: '/search',
      EXPORT: '/export',
      IMPORT: '/import',
      REGISTER: '/register'
    }
  },
  
  // 购物车管理接口
  CART: {
    BASE: '/api/public/cart',
    ENDPOINTS: {
      ADD: ''
    }
  },
  
  // 支付管理接口
  PAYMENT: {
    BASE: '/api/public/payment',
    ENDPOINTS: {
      LIST: '',
      CREATE: '',
      UPDATE: '/{id}'
    }
  },
  
  // 旅游推荐接口
  RECOMMEND: {
    BASE: '/api/public/recommend',
    ENDPOINTS: {
      LIST: '',
      CREATE: '',
      UPDATE: '/{id}',
      DELETE: '/{id}'
    }
  },
  
  // 省份管理接口
  PROVINCE: {
    BASE: '/api/public/provinces',
    ENDPOINTS: {
      LIST: '',
      DETAIL: '/{id}'
    }
  },
  
  // 景点管理接口
  SCENIC: {
    BASE: '/api/public/scenic',
    ENDPOINTS: {
      LIST: '',
      CREATE: '',
      UPDATE: '/{id}',
      DELETE: '/{id}'
    }
  },
  
  // 美食管理接口
  FOOD: {
    BASE: '/api/public/foods',
    ENDPOINTS: {
      LIST: '',
      SEARCH: '/search',
      DETAIL: '/{id}',
      CREATE: '',
      UPDATE: '/{id}',
      DELETE: '/{id}'
    }
  },
  
  // 订单管理接口
  ORDER: {
    BASE: '/api/public/order',
    ENDPOINTS: {
      LIST: '',
      DETAIL: '/{id}',
      CREATE: '',
      UPDATE: '/{id}',
      DELETE: '/{id}'
    }
  },
  
  // 系统监控接口
  MONITOR: {
    BASE: '/api',
    ENDPOINTS: {
      SERVER_MONITOR: '/server-monitor'
    }
  },
  
  // 认证管理接口
  AUTH: {
    BASE: '/api/auth',
    ENDPOINTS: {
      SEND_CODE: '/send-code',
      EMAIL_LOGIN: '/Emaillogin'
    }
  },
  
  // 登录日志接口
  LOGIN_LOG: {
    BASE: '/api/public/system/log/operation',
    ENDPOINTS: {
      LIST: '/list'
    }
  },
  
  // 旅游博客接口
  BLOG: {
    BASE: '/api/public/blogs',
    ENDPOINTS: {
      LIST: '',
      DETAIL: '/{id}',
      CREATE: '',
      UPDATE: '/{id}',
      DELETE: '/{id}',
      LIKE: '/{id}/like',
      FAVORITE: '/{id}/favorite'
    }
  },
  
  // 旅游卡管理接口
  TRAVEL_CARD: {
    BASE: '/api/public/travelcard',
    ENDPOINTS: {
      LIST: '',
      DETAIL: '/{id}',
      CREATE: '',
      UPDATE: '/{id}',
      DELETE: '/{id}',
      SEARCH_NAME: '/search/name',
      SEARCH_KEYWORD: '/search/keyword',
      SEARCH_CATEGORY: '/search/category',
      SEARCH_BADGE: '/search/badge'
    }
  },
  
  // 收藏管理接口
  COLLECTION: {
    BASE: '/api/public/travel-collections',
    ENDPOINTS: {
      LIST: '',
      DETAIL: '/{id}',
      USER: '/user/{username}',
      PAGE: '/page',
      COUNT: '/count',
      CREATE: '',
      UPDATE: '/{id}',
      DELETE: '/{id}',
      ADVANCED_SEARCH: '/advanced-search'
    }
  },
  
  // 酒店管理接口
  HOTEL: {
    BASE: '/api/public/hotel',
    ENDPOINTS: {
      LIST: '',
      CREATE: '',
      UPDATE: '/{id}',
      DELETE: '/{id}'
    }
  },
  
  // 目的地管理接口
  DESTINATION: {
    BASE: '/api/public/destination',
    ENDPOINTS: {
      LIST: '',
      CREATE: '',
      UPDATE: '/{id}',
      DELETE: '/{id}'
    }
  },
  
  // 轮播图管理接口
  CAROUSEL: {
    BASE: '/api/public/travelcarousel',
    ENDPOINTS: {
      LIST: '',
      CREATE: '',
      UPDATE: '/{id}',
      DELETE: '/{id}'
    }
  },
  
  // 旅游笔记接口
  NOTE: {
    BASE: '/api/public/notes',
    ENDPOINTS: {
      DETAIL: '/{id}',
      LIST: '',
      CREATE: '',
      UPDATE: '/{id}',
      DELETE: '/{id}',
      SEARCH: '/search'
    }
  },
  
  // 旅游新闻接口
  NEWS: {
    BASE: '/api/public/news',
    ENDPOINTS: {
      LIST: '',
      DETAIL: '/{id}',
      CREATE: '',
      UPDATE: '/{id}',
      DELETE: '/{id}',
      LIKE: '/like',
      COLLECT: '/collect'
    }
  },
  
  // 攻略组接口
  STRATEGY_GROUP: {
    BASE: '/api/public/strategy-groups',
    ENDPOINTS: {
      LIST: '',
      DETAIL: '/{id}',
      CREATE: '',
      UPDATE: '/{id}',
      DELETE: '/{id}',
      SEARCH_TITLE: '/search/title'
    }
  },
  
  // 安全提示接口
  SAFETY_TIPS: {
    BASE: '/api/public/safety-tips',
    ENDPOINTS: {
      LIST: '',
      DETAIL: '/{id}',
      COUNT: '/count',
      CREATE: '',
      BATCH_CREATE: '/batch',
      CONDITION_SEARCH: '/condition',
      CONDITION_COUNT: '/condition-count',
      UPDATE: '/{id}',
      BATCH_UPDATE: '/batch',
      DELETE: '/{id}',
      BATCH_DELETE: '/batch'
    }
  },
  
  // 帖子管理接口
  POST: {
    BASE: '/api/public/posts',
    ENDPOINTS: {
      LIST: '',
      DETAIL: '/{id}',
      CREATE: '',
      UPDATE: '/{id}',
      DELETE: '/{id}',
      SEARCH_DESTINATION: '/search/destination',
      SEARCH_TAG: '/search/tag'
    }
  },
  
  // 旅游世界特性接口
  TRAVEL_WORLD: {
    BASE: '/api/public/travelworld',
    ENDPOINTS: {
      LIST: '',
      CREATE: '',
      UPDATE: '/{id}',
      DELETE: '/{id}'
    }
  }
};