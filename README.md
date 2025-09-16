# SpiritualPressure - 旅游管理平台

## 项目简介

SpiritualPressure 是一个基于 Spring Boot 3.4.3 和 Vue 3 的全栈旅游管理平台，提供完整的旅游服务解决方案，包括景点管理、酒店预订、美食推荐、旅游攻略、订单管理等功能。

## 技术栈

### 后端技术
- **框架**: Spring Boot 3.4.3
- **安全**: Spring Security + JWT
- **数据库**: MySQL + MyBatis + MyBatis通用Mapper
- **缓存**: Redis + Spring Data Redis
- **实时通信**: WebSocket + STOMP协议
- **API文档**: Springdoc OpenAPI (Swagger)
- **验证**: Spring Boot Validation
- **Excel处理**: EasyExcel
- **邮件服务**: Jakarta Mail
- **系统监控**: OSHI
- **Java版本**: JDK 17

### 前端技术
- **框架**: Vue 3 + Element Plus
- **构建工具**: Vite
- **状态管理**: Pinia
- **路由**: Vue Router 4
- **HTTP客户端**: Axios
- **图表**: ECharts + Chart.js
- **地图**: 高德地图API + Mapbox GL
- **Markdown**: Marked
- **图标**: Lucide Vue + Element Plus Icons

## 项目结构

```
spiritualPressure/
├── src/main/java/com/example/travel/
│   ├── aspect/           # AOP切面（日志、限流）
│   ├── common/           # 通用类（结果封装、支付方式）
│   ├── config/          # 配置类（安全、Redis、MyBatis、Swagger、WebSocket）
│   ├── controller/      # 控制器层（20+个业务控制器）
│   ├── dao/             # 数据访问层
│   ├── dto/             # 数据传输对象
│   ├── entity/          # 实体类（用户、订单、景点、酒店等）
│   ├── exception/       # 异常处理
│   ├── filter/          # 过滤器（JWT认证）
│   ├── handler/         # 类型处理器
│   ├── listener/        # 监听器
│   ├── service/         # 服务层接口和实现
│   ├── utils/           # 工具类（JWT、Redis、文件、邮件等）
│   └── websocket/       # WebSocket相关（配置、处理器、拦截器）
├── src/main/resources/
│   ├── static/          # 静态资源（Bootstrap、错误页面）
│   └── mapper/          # MyBatis映射文件
├── spiritualPressure-ui/ # Vue 3前端项目
│   ├── src/
│   │   ├── api/         # API接口
│   │   ├── assets/      # 静态资源
│   │   ├── components/  # 组件
│   │   ├── router/      # 路由配置
│   │   ├── stores/      # 状态管理
│   │   └── views/       # 页面视图
│   └── vite.config.js   # Vite配置
└── sql/                 # 数据库脚本
```

## 主要功能模块

### 1. 用户管理
- 用户注册、登录、JWT认证
- 用户信息管理、权限控制
- 登录日志记录

### 2. 旅游服务
- **景点管理**: 景点信息、评价、门票销售
- **酒店预订**: 酒店信息、房间预订、价格管理
- **美食推荐**: 当地美食、餐厅信息、推荐系统
- **旅游攻略**: 游记分享、攻略发布、收藏功能

### 3. 订单支付
- 购物车管理
- 订单创建、状态跟踪
- 多种支付方式集成
- 支付结果回调处理

### 4. 内容管理
- 旅游新闻发布
- 轮播图管理
- 安全提示信息
- 旅游卡片推荐

### 5. 社区互动
- 旅游帖子发布
- 评论互动
- 收藏点赞功能
- 攻略分组管理

### 6. 系统监控
- 服务器性能监控（CPU、内存、磁盘、网络）
- 进程信息监控
- 系统健康状态检查

### 7. 安全特性
- JWT令牌认证
- Spring Security权限控制
- 接口限流保护
- 数据加密传输
- 自定义错误页面（401、403）

### 8. 实时通信 (WebSocket)
- **即时消息**: 用户间实时聊天和群组聊天
- **通知推送**: 订单状态变更、系统通知实时推送
- **在线状态**: 用户在线/离线状态实时更新
- **会话管理**: WebSocket连接建立、维护和关闭
- **消息持久化**: 聊天消息的存储和检索
- **心跳检测**: 连接健康状态监测和重连机制

### 小程序技术

SpiritualPressure 提供了基于 uni-app x 的多端小程序解决方案，支持微信小程序、支付宝小程序、百度小程序等多平台发布。

#### 技术架构
- **开发框架**: uni-app x (基于 Vue 3 语法)
- **构建工具**: HBuilderX + uni-app 编译器
- **UI框架**: uni-ui 官方组件库 + 自定义组件
- **状态管理**: Vue 3 Composition API + Pinia
- **网络请求**: Axios + 自定义请求拦截器
- **路由管理**: uni-app 原生路由系统
- **存储方案**: uni-app 本地存储 + 状态持久化
- **多端适配**: 条件编译 + 平台特性检测

#### 核心特性

##### 1. 多端发布能力
- **微信小程序**: 完整支持微信生态API
- **支付宝小程序**: 适配支付宝开放平台
- **百度小程序**: 支持百度智能小程序
- **其他平台**: 快应用、H5、App等多端支持

##### 2. 性能优化
- **渲染优化**: 虚拟列表、图片懒加载、组件复用
- **包体积优化**: 代码分割、资源压缩、按需引入
- **网络优化**: 请求缓存、图片CDN、接口合并
- **启动优化**: 分包加载、预加载、骨架屏

##### 3. 开发体验
- **热重载**: 实时预览开发效果
- **调试工具**: 集成 Chrome DevTools
- **类型支持**: TypeScript + JSDoc 注释
- **代码规范**: ESLint + Prettier 代码格式化

#### 项目结构
```
spiritualPressure-uniApp/
├── api/                 # API接口模块
│   ├── blog.js         # 博客接口
│   ├── carousel.js     # 轮播图接口
│   ├── cart.js         # 购物车接口
│   ├── collection.js    # 收藏接口
│   ├── destination.js  # 目的地接口
│   ├── food.js         # 美食接口
│   ├── hotel.js        # 酒店接口
│   ├── index.js        # 接口统一导出
│   ├── loginLog.js     # 登录日志接口
│   ├── monitor.js      # 监控接口
│   ├── news.js         # 新闻接口
│   ├── note.js         # 笔记接口
│   ├── order.js        # 订单接口
│   ├── payment.js      # 支付接口
│   ├── post.js         # 帖子接口
│   ├── province.js     # 省份接口
│   ├── recommend.js    # 推荐接口
│   ├── safetyTips.js   # 安全提示接口
│   ├── scenic.js       # 景点接口
│   ├── strategyGroup.js # 攻略组接口
│   ├── travelCard.js   # 旅游卡接口
│   ├── travelWorld.js  # 旅游世界接口
│   └── user.js         # 用户接口
├── components/         # 公共组件
├── pages/             # 页面组件
│   ├── address/       # 收货地址
│   ├── attraction/     # 景点相关
│   ├── collection/     # 我的收藏
│   ├── coupon/        # 优惠券
│   ├── food/          # 美食相关
│   ├── help/          # 帮助中心
│   ├── hotel/         # 酒店相关
│   ├── index/         # 首页
│   ├── login/         # 登录页面
│   ├── order/         # 订单相关
│   ├── profile/       # 个人资料
│   ├── register/      # 注册页面
│   ├── security/      # 安全中心
│   ├── setting/       # 设置页面
│   ├── strategy/      # 旅游攻略
│   └── user/          # 用户中心
├── static/            # 静态资源
│   ├── app/           # App图标
│   ├── logo.png       # 应用Logo
│   └── tabbar/        # 底部导航图标
├── stores/            # 状态管理
│   ├── cart.js        # 购物车状态
│   └── user.js        # 用户状态
├── utils/             # 工具函数
│   ├── auth.js        # 认证工具
│   ├── config.js      # 配置管理
│   └── request.js     # 网络请求
├── App.uvue           # 应用入口
├── main.uts           # 主程序入口
├── manifest.json      # 应用配置
├── pages.json         # 页面配置
└── package.json       # 项目依赖
```

#### 核心功能页面

##### 1. 首页 (index.uvue)
- 轮播图展示旅游推荐内容
- 功能导航快捷入口
- 热门景点推荐列表
- 旅游攻略内容展示
- 实时数据加载与缓存

##### 2. 景点模块
- 景点列表分页展示
- 景点详情信息页面
- 门票预订功能
- 地理位置导航

##### 3. 酒店模块  
- 酒店列表与筛选
- 酒店详情展示
- 房间预订流程
- 订单管理功能

##### 4. 美食模块
- 美食推荐列表
- 餐厅详情信息
- 在线下单功能
- 口味偏好设置

##### 5. 用户中心
- 个人信息管理
- 订单历史查询
- 收藏内容管理
- 设置与偏好配置

#### 技术实现细节

##### 请求封装
```javascript
// 统一的请求拦截器
const requestInterceptor = (config) => {
  // 添加认证token
  const token = uni.getStorageSync('token');
  if (token) {
    config.header = config.header || {};
    config.header['Authorization'] = `Bearer ${token}`;
  }
  
  // 添加基础URL
  if (!config.url.startsWith('http')) {
    config.url = BASE_URL + config.url;
  }
  
  return config;
};

// 响应拦截器处理统一错误
const responseInterceptor = (response) => {
  if (response.statusCode === 200) {
    return response.data;
  } else if (response.statusCode === 401) {
    // token过期处理
    uni.removeStorageSync('token');
    uni.navigateTo({ url: '/pages/login/login' });
    return Promise.reject(new Error('登录已过期'));
  }
};
```

##### 状态管理
```javascript
// 使用Pinia进行状态管理
import { defineStore } from 'pinia';

export const useUserStore = defineStore('user', {
  state: () => ({
    userInfo: null,
    token: uni.getStorageSync('token') || null
  }),
  
  actions: {
    async login(credentials) {
      const response = await userApi.login(credentials);
      this.token = response.data.token;
      uni.setStorageSync('token', this.token);
      await this.getUserInfo();
    },
    
    async getUserInfo() {
      const response = await userApi.getInfo();
      this.userInfo = response.data;
    }
  }
});
```

##### 多端适配
```javascript
// 条件编译处理平台差异
// #ifdef MP-WEIXIN
console.log('微信小程序环境');
// #endif

// #ifdef MP-ALIPAY  
console.log('支付宝小程序环境');
// #endif

// 平台特性检测
const isWeixin = uni.getSystemInfoSync().platform === 'devtools';
const isAlipay = uni.getSystemInfoSync().platform === 'alipay';
```

#### 开发与构建

##### 环境要求
- Node.js 16+
- HBuilderX 最新版本
- 各小程序平台开发者工具

##### 开发命令
```bash
# 安装依赖
npm install

# 启动开发服务器  
npm run dev

# 构建生产版本
npm run build

# 预览构建结果
npm run preview
```

##### 发布流程
1. **开发调试**: 使用 HBuilderX 进行真机调试
2. **代码审核**: 通过 ESLint 进行代码规范检查
3. **测试验证**: 在多平台进行功能测试
4. **构建打包**: 生成各平台小程序包
5. **平台提交**: 上传到对应小程序平台审核
6. **版本发布**: 审核通过后发布上线

#### 性能监控

##### 关键指标监控
- **启动时间**: 冷启动、热启动耗时
- **页面渲染**: 首屏渲染时间、白屏时间
- **网络请求**: API响应时间、成功率
- **内存使用**: 内存占用峰值、泄漏检测
- **用户行为**: 页面停留、功能使用频率

##### 优化策略
- **图片优化**: WebP格式、懒加载、CDN加速
- **代码分割**: 按页面分包、减少主包体积
- **缓存策略**: 接口数据缓存、本地存储优化
- **预加载**: 关键资源预加载、数据预取


## 快速开始

### 环境要求
- JDK 17+
- MySQL 8.0+
- Redis 6.0+
- Node.js 16+
- Maven 3.6+

### 后端启动
1. 配置数据库连接信息
2. 导入SQL脚本初始化数据库
3. 运行Maven构建: `mvn clean install`
4. 启动应用: `mvn spring-boot:run`

### 前端启动
1. 进入前端目录: `cd spiritualPressure-ui`
2. 安装依赖: `npm install`
3. 启动开发服务器: `npm run dev`
4. 访问: http://localhost:9527

### API文档
启动后访问: http://localhost:2025/swagger-ui.html

## 配置说明

### 数据库配置
在 `application.properties` 中配置MySQL连接:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/travel_db
spring.datasource.username=root
spring.datasource.password=123456
```

### Redis配置
```properties
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.password=
```

### JWT配置
```properties
jwt.secret=your-secret-key
jwt.expiration=86400000
```

## 部署说明

### 后端部署
1. 打包: `mvn clean package`
2. 运行: `java -jar target/demo-0.0.1-SNAPSHOT.jar`

### 前端部署
1. 构建: `npm run build`
2. 部署到Nginx或静态服务器

## 错误页面

项目提供了自定义错误页面:
- `401.html` - 未授权访问
- `403.html` - 禁止访问

基于Bootstrap 5设计，支持响应式布局。

## 开发团队

- 后端开发: Spring Boot, MyBatis, Spring Security
- 前端开发: Vue 3, Element Plus, Vite
- 数据库设计: MySQL
- 运维部署: Docker, Nginx

## 许可证

本项目基于MIT许可证开源。

## 支持与反馈

如有问题或建议，请提交Issue或联系开发团队。

## 项目运行截图
--  登录页面
![login.png](/src/main/resources/static/image/login.png)

--  注册页面
![registered.png](/src/main/resources/static/image/registered.png)

--  项目首页
![home.png](/src/main/resources/static/image/home.png)

--  支付页面
![payment.png](/src/main/resources/static/image/payment.png)

--  个人中心
![personalCenter.png](/src/main/resources/static/image/personalCenter.png)

--  后台管理
![systemBackground.png](/src/main/resources/static/image/systemBackground.png)