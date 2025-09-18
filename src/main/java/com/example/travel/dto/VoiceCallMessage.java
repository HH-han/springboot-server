package com.example.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 语音通话消息DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoiceCallMessage {
    
    /**
     * 通话类型
     */
    private CallType type;
    
    /**
     * 发起者
     */
    private String caller;
    
    /**
     * 接收者
     */
    private String receiver;
    
    /**
     * 通话ID
     */
    private String callId;
    
    /**
     * 音频数据（用于实时传输）
     */
    private String audioData;
    
    /**
     * 时间戳（毫秒时间戳）
     */
    private Long timestamp;
    
    /**
     * 通话状态
     */
    private CallStatus status;
    
    /**
     * 通话类型枚举
     */
    public enum CallType {
        CALL_REQUEST,      // 通话请求
        CALL_ACCEPT,       // 通话接受
        CALL_REJECT,       // 通话拒绝
        CALL_END,          // 通话结束
        AUDIO_DATA,        // 音频数据传输
        ICE_CANDIDATE,     // WebRTC ICE候选
        SDP_OFFER,         // WebRTC SDP Offer
        SDP_ANSWER         // WebRTC SDP Answer
    }
    
    /**
     * 通话状态枚举
     */
    public enum CallStatus {
        RINGING,          // 振铃中
        IN_PROGRESS,      // 通话中
        ENDED,            // 通话结束
        MISSED,           // 未接来电
        REJECTED          // 已拒绝
    }
    
    /**
     * 便捷构造方法
     */
    public VoiceCallMessage(CallType type, String caller, String receiver, String callId) {
        this.type = type;
        this.caller = caller;
        this.receiver = receiver;
        this.callId = callId;
        this.timestamp = System.currentTimeMillis();
    }
    
    /**
     * 便捷构造方法（包含音频数据）
     */
    public VoiceCallMessage(CallType type, String caller, String receiver, String callId, String audioData) {
        this.type = type;
        this.caller = caller;
        this.receiver = receiver;
        this.callId = callId;
        this.audioData = audioData;
        this.timestamp = System.currentTimeMillis();
    }
    
    /**
     * 手动添加setAudioData方法（Lombok可能未正确生成）
     */
    public void setAudioData(String audioData) {
        this.audioData = audioData;
    }
    
    /**
     * 手动添加getReceiver方法
     */
    public String getReceiver() {
        return receiver;
    }
    
    /**
     * 手动添加getCallId方法
     */
    public String getCallId() {
        return callId;
    }
    
    /**
     * 手动添加getAudioData方法
     */
    public String getAudioData() {
        return audioData;
    }
    
    /**
     * 手动添加setCallId方法
     */
    public void setCallId(String callId) {
        this.callId = callId;
    }
    
    /**
     * 手动添加setStatus方法
     */
    public void setStatus(CallStatus status) {
        this.status = status;
    }
}