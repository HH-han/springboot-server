package com.example.travel.utils;

public class ConsoleUtils {
    private static final String ASCII_ART =
            "███████╗████████╗ █████╗ ██████╗ ████████╗███████╗██████╗     ███████╗██╗   ██╗ ██████╗ ██████╗███████╗███████╗███████╗███████╗██╗   ██╗██╗     ██╗  ██╗   ██╗\n" +
            "██╔════╝╚══██╔══╝██╔══██╗██╔══██╗╚══██╔══╝██╔════╝██╔══██╗    ██╔════╝██║   ██║██╔════╝██╔════╝██╔════╝██╔════╝██╔════╝██╔════╝██║   ██║██║     ██║  ╚██╗ ██╔╝\n" +
            "███████╗   ██║   ███████║██████╔╝   ██║   █████╗  ██║  ██║    ███████╗██║   ██║██║     ██║     █████╗  ███████╗███████╗█████╗  ██║   ██║██║     ██║   ╚████╔╝ \n" +
            "╚════██║   ██║   ██╔══██║██╔══██╗   ██║   ██╔══╝  ██║  ██║    ╚════██║██║   ██║██║     ██║     ██╔══╝  ╚════██║╚════██║██╔══╝  ██║   ██║██║     ██║    ╚██╔╝  \n" +
            "███████║   ██║   ██║  ██║██║  ██║   ██║   ███████╗██████╔╝    ███████║╚██████╔╝╚██████╗╚██████╗███████╗███████║███████║██║     ╚██████╔╝███████╗███████╗██║   \n" +
            "╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═════╝     ╚══════╝ ╚═════╝  ╚═════╝ ╚═════╝╚══════╝╚══════╝╚══════╝╚═╝      ╚═════╝ ╚══════╝╚══════╝╚═╝   \n" +
            "                                                                                                                                                              ";

    public static void printStartupMessages() {
        printAsciiArt(ASCII_ART);
        printSuccessMessage("");
        printUrl("","");
    }
    
    public static void printAsciiArt(String art) {
        System.out.println(art);
    }
    
    public static void printSuccessMessage(String message) {
        System.out.println("🚀" + "启动成功！" + "🚀");
    }
    
    public static void printUrl(String name, String url) {
        System.out.println("🗂️"+"项目结构:\n"+"```\n" +
                "spiritualPressure/\n" +
                "├── src/main/java/com/example/travel/\n" +
                "│   ├── aspect/           # AOP切面（日志、限流）\n" +
                "│   ├── common/           # 通用类（结果封装、支付方式）\n" +
                "│   ├── config/          # 配置类（安全、Redis、MyBatis、Swagger）\n" +
                "│   ├── controller/      # 控制器层（20+个业务控制器）\n" +
                "│   ├── dao/             # 数据访问层\n" +
                "│   ├── dto/             # 数据传输对象\n" +
                "│   ├── entity/          # 实体类（用户、订单、景点、酒店等）\n" +
                "│   ├── exception/       # 异常处理\n" +
                "│   ├── filter/          # 过滤器（JWT认证）\n" +
                "│   ├── handler/         # 类型处理器\n" +
                "│   ├── listener/        # 监听器\n" +
                "│   ├── service/         # 服务层接口和实现\n" +
                "│   └── utils/           # 工具类（JWT、Redis、文件、邮件等）\n" +
                "├── src/main/resources/\n" +
                "│   ├── static/          # 静态资源（Bootstrap、错误页面）\n" +
                "│   └── mapper/          # MyBatis映射文件\n" +
                "├── spiritualPressure-ui/ # Vue 3前端项目\n" +
                "│   ├── src/\n" +
                "│   │   ├── api/         # API接口\n" +
                "│   │   ├── assets/      # 静态资源\n" +
                "│   │   ├── components/  # 组件\n" +
                "│   │   ├── router/      # 路由配置\n" +
                "│   │   ├── stores/      # 状态管理\n" +
                "│   │   └── views/       # 页面视图\n" +
                "│   └── vite.config.js   # Vite配置\n" +
                "├── spiritualPressure-uniApp/ # 小程序项目\n" +
                "│   ├── api/      # API接口\n" +
                "│   ├── components/      # 组件\n" +
                "│   ├── pages/      # 页面\n" +
                "│   ├── static/      # 静态资源\n" +
                "│   ├── store/      # 状态管理\n" +
                "│   ├── utils/      # 工具类\n" +
                "│   └── App.vue      # 应用入口\n" +
                "└── spiritualPressure-db # 数据库脚本\n" +
                "```");
        System.out.println("🌍" + "后端" + "地址:" + "http://localhost:2025/");
        System.out.println("📃"+"接口文档"+"地址:"+"http://localhost:2025/apiDocument.html");
        System.out.println("🌌" + "前端" + "地址:" + "http://localhost:9527/");
        System.out.println("🧑‍🤝‍🧑"+"即时通讯" + "地址:" + "http://localhost:2025/im-api-documentation.html");
        System.out.println("🧺"+"Github地址:" + "https://github.com/HH-han");
        System.out.println("🐵"+"程序猿:"+"bolan");
    }
}