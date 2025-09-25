package com.example.travel.utils;

import org.springframework.stereotype.Component;

/**
 * 用户代理信息工具类
 * 用于解析HTTP请求头中的User-Agent信息
 */
@Component
public class UserAgentUtils {

    /**
     * 从User-Agent字符串中提取浏览器信息
     * @param userAgent User-Agent字符串
     * @return 浏览器信息
     */
    public String getBrowser(String userAgent) {
        if (userAgent == null || userAgent.isEmpty()) {
            return "未知浏览器";
        }
        
        userAgent = userAgent.toLowerCase();
        
        if (userAgent.contains("msie") || userAgent.contains("trident")) {
            return "Internet Explorer";
        } else if (userAgent.contains("edg")) {
            return "Microsoft Edge";
        } else if (userAgent.contains("chrome")) {
            return "Chrome";
        } else if (userAgent.contains("safari")) {
            return "Safari";
        } else if (userAgent.contains("firefox")) {
            return "Firefox";
        } else if (userAgent.contains("opera")) {
            return "Opera";
        } else {
            return "未知浏览器";
        }
    }

    /**
     * 从User-Agent字符串中提取操作系统信息
     * @param userAgent User-Agent字符串
     * @return 操作系统信息
     */
    public String getOs(String userAgent) {
        if (userAgent == null || userAgent.isEmpty()) {
            return "未知操作系统";
        }
        
        userAgent = userAgent.toLowerCase();
        
        if (userAgent.contains("win")) {
            if (userAgent.contains("windows nt 11.0")) {
                return "Windows 11";
            }else if (userAgent.contains("windows nt 10.0")) {
                return "Windows 10";
            } else if (userAgent.contains("windows nt 6.3")) {
                return "Windows 8.1";
            } else if (userAgent.contains("windows nt 6.2")) {
                return "Windows 8";
            } else if (userAgent.contains("windows nt 6.1")) {
                return "Windows 7";
            } else if (userAgent.contains("windows nt 6.0")) {
                return "Windows Vista";
            } else if (userAgent.contains("windows nt 5.1")) {
                return "Windows XP";
            } else if (userAgent.contains("windows nt 5.0")) {
                return "Windows 2000";
            } else {
                return "Windows";
            }
        } else if (userAgent.contains("mac")) {
            return "Mac OS";
        } else if (userAgent.contains("linux")) {
            return "Linux";
        } else if (userAgent.contains("android")) {
            return "Android";
        } else if (userAgent.contains("iphone") || userAgent.contains("ipad")) {
            return "iOS";
        } else {
            return "未知操作系统";
        }
    }
}