package com.example.travel.config;

import com.example.travel.interceptor.WebSocketChannelInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

/**
 * WebSocket配置类
 * 配置STOMP协议和消息代理
 */
@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final WebSocketChannelInterceptor webSocketChannelInterceptor;

    public WebSocketConfig(WebSocketChannelInterceptor webSocketChannelInterceptor) {
        this.webSocketChannelInterceptor = webSocketChannelInterceptor;
    }



    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        // 添加WebSocket通道拦截器
        registration.interceptors(webSocketChannelInterceptor);
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        // 添加WebSocket通道拦截器
        registration.interceptors(webSocketChannelInterceptor);
    }

    /**
     * 注册STOMP端点
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册STOMP端点，允许SockJS备用选项
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
        
        // 注册语音通话专用的WebSocket端点
        registry.addEndpoint("/voice")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
    

    
    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        // 显式配置MappingJackson2MessageConverter作为消息转换器
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setStrictContentTypeMatch(false);
        messageConverters.add(converter);
        return false;
    }
    
    /**
     * 配置消息代理（增加消息大小限制）
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 启用简单内存消息代理，目的地前缀为 /topic 和 /queue
        config.enableSimpleBroker("/topic", "/queue");
        // 设置应用程序目的地前缀
        config.setApplicationDestinationPrefixes("/app");
        // 设置用户目的地前缀
        config.setUserDestinationPrefix("/user");
        
        // 配置消息大小限制（支持语音数据传输）
        config.setPreservePublishOrder(true);
    }
}