package com.example.travel.utils;

import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP地址定位工具类
 * 用于根据IP地址获取地理位置信息
 */
@Component
public class IpLocationUtils {

    /**
     * 根据IP地址获取地理位置信息
     * @param ip IP地址
     * @return 地理位置信息
     */
    public String getLocation(String ip) {
        if (ip == null || ip.isEmpty()) {
            return "未知地点";
        }
        
        // 本地地址和内网地址
        if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1") || 
            ip.startsWith("192.168.") || ip.startsWith("10.") || 
            ip.startsWith("172.16.") || ip.startsWith("172.17.") || 
            ip.startsWith("172.18.") || ip.startsWith("172.19.") || 
            ip.startsWith("172.20.") || ip.startsWith("172.21.") || 
            ip.startsWith("172.22.") || ip.startsWith("172.23.") || 
            ip.startsWith("172.24.") || ip.startsWith("172.25.") || 
            ip.startsWith("172.26.") || ip.startsWith("172.27.") || 
            ip.startsWith("172.28.") || ip.startsWith("172.29.") || 
            ip.startsWith("172.30.") || ip.startsWith("172.31.")) {
            return "内网IP";
        }
        
        // 这里可以集成第三方IP定位服务，如淘宝IP库、百度IP定位等
        // 目前先返回简单信息
        
        try {
            InetAddress address = InetAddress.getByName(ip);
            String hostName = address.getHostName();
            
            // 如果是公网IP，可以根据需要集成第三方API
            // 这里暂时返回简单信息
            return "公网IP";
            
        } catch (UnknownHostException e) {
            return "未知地点";
        }
    }
    
    /**
     * 判断是否为IPv6地址
     * @param ip IP地址
     * @return 是否为IPv6
     */
    public boolean isIPv6(String ip) {
        return ip != null && ip.contains(":");
    }
    
    /**
     * 判断是否为IPv4地址
     * @param ip IP地址
     * @return 是否为IPv4
     */
    public boolean isIPv4(String ip) {
        return ip != null && ip.matches("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
    }
}