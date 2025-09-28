package com.example.travel.service;

import com.example.travel.dto.VoiceCallMessage;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
/**
 * 语音通话服务接口
 */
public interface VoiceCallService {
    
    /**
     * 发起语音通话
     * @param caller 发起者用户名
     * @param receiver 接收者用户名
     * @return 通话ID
     */
    String initiateCall(String caller, String receiver);
    
    /**
     * 接受通话
     * @param callId 通话ID
     * @param receiver 接收者用户名
     */
    void acceptCall(String callId, String receiver);
    
    /**
     * 拒绝通话
     * @param callId 通话ID
     * @param receiver 接收者用户名
     */
    void rejectCall(String callId, String receiver);
    
    /**
     * 结束通话
     * @param callId 通话ID
     * @param username 用户名
     */
    void endCall(String callId, String username);
    
    /**
     * 发送音频数据
     * @param callId 通话ID
     * @param username 用户名
     * @param audioData 音频数据
     */
    void sendAudioData(String callId, String username, String audioData);
    
    /**
     * 发送ICE候选
     * @param callId 通话ID
     * @param username 用户名
     * @param iceCandidate ICE候选信息
     */
    void sendIceCandidate(String callId, String username, String iceCandidate);
    
    /**
     * 发送SDP Offer
     * @param callId 通话ID
     * @param username 用户名
     * @param sdpOffer SDP Offer
     */
    void sendSdpOffer(String callId, String username, String sdpOffer);
    
    /**
     * 发送SDP Answer
     * @param callId 通话ID
     * @param username 用户名
     * @param sdpAnswer SDP Answer
     */
    void sendSdpAnswer(String callId, String username, String sdpAnswer);
    
    /**
     * 获取活跃的通话
     * @param callId 通话ID
     * @return 通话信息
     */
    Map<String, Object> getActiveCall(String callId);
    
    /**
     * 检查用户是否在通话中
     * @param username 用户名
     * @return 是否在通话中
     */
    boolean isUserInCall(String username);
    
    /**
     * 清理过期的通话
     */
    void cleanupExpiredCalls();
}