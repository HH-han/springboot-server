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
        System.out.println("🌍" + "后端" + "地址：" + "http://localhost:2025/");
        System.out.println("🌍" + "前端" + "地址：" + "http://localhost:9527/");
    }
}