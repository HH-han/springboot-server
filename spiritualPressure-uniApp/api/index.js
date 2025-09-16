// API接口统一导出文件

export * from '../utils/config.js';
export * from '../utils/request.js';

export * from '../utils/auth.js';
export * from './blog.js';
export * from './cart.js';
export * from './collection.js';
export * from './destination.js';
export * from './food.js';
export * from './hotel.js';
export * from './loginLog.js';
export * from './monitor.js';
export * from './news.js';
export * from './note.js';
export * from './order.js';
export * from './payment.js';
export * from './post.js';
export * from './province.js';
export * from './recommend.js';
export * from './safetyTips.js';
export * from './scenic.js';
export * from './strategyGroup.js';
export * from './travelCard.js';
export * from './travelWorld.js';
export * from './user.js';
export * from './carousel.js';

// 统一API对象
export const api = {
  auth: {},
  blog: {},
  cart: {},
  collection: {},
  destination: {},
  food: {},
  hotel: {},
  loginLog: {},
  monitor: {},
  news: {},
  note: {},
  order: {},
  payment: {},
  post: {},
  province: {},
  recommend: {},
  safetyTips: {},
  scenic: {},
  strategyGroup: {},
  travelCard: {},
  travelWorld: {},
  user: {},
  carousel: {}
};

// 动态导入所有API模块并赋值给统一API对象
const importAllApis = async () => {
  const modules = await Promise.all([
    import('../utils/auth.js'),
    import('./blog.js'),
    import('./cart.js'),
    import('./collection.js'),
    import('./destination.js'),
    import('./food.js'),
    import('./hotel.js'),
    import('./loginLog.js'),
    import('./monitor.js'),
    import('./news.js'),
    import('./note.js'),
    import('./order.js'),
    import('./payment.js'),
    import('./post.js'),
    import('./province.js'),
    import('./recommend.js'),
    import('./safetyTips.js'),
    import('./scenic.js'),
    import('./strategyGroup.js'),
    import('./travelCard.js'),
    import('./travelWorld.js'),
    import('./user.js'),
    import('./carousel.js')
  ]);
  
  api.auth = modules[0];
  api.blog = modules[1];
  api.cart = modules[2];
  api.collection = modules[3];
  api.destination = modules[4];
  api.food = modules[5];
  api.hotel = modules[6];
  api.loginLog = modules[7];
  api.monitor = modules[8];
  api.news = modules[9];
  api.note = modules[10];
  api.order = modules[11];
  api.payment = modules[12];
  api.post = modules[13];
  api.province = modules[14];
  api.recommend = modules[15];
  api.safetyTips = modules[16];
  api.scenic = modules[17];
  api.strategyGroup = modules[18];
  api.travelCard = modules[19];
  api.travelWorld = modules[20];
  api.user = modules[21];
  api.carousel = modules[22];
};

// 立即执行导入
importAllApis();