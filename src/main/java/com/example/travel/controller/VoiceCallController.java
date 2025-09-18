package com.example.travel.controller;

import com.example.travel.dto.VoiceCallMessage;
import com.example.travel.service.VoiceCallService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * 语音通话WebSocket控制器
 * 处理实时语音通话相关消息
 */
@Controller
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class VoiceCallController {
    
    private static final Logger log = LoggerFactory.getLogger(VoiceCallController.class);
    
    private final VoiceCallService voiceCallService;
    
    /**
     * 处理语音通话请求
     */
    @MessageMapping("/voice/call/request")
    @SendToUser("/queue/voice/call")
    public VoiceCallMessage handleCallRequest(VoiceCallMessage message, Principal principal) {
        String caller = principal.getName();
        String receiver = message.getReceiver();
        
        try {
            String callId = voiceCallService.initiateCall(caller, receiver);
            message.setCallId(callId);
            message.setStatus(VoiceCallMessage.CallStatus.RINGING);
            log.info("语音通话请求处理成功: {} -> {}, 通话ID: {}", caller, receiver, callId);
        } catch (Exception e) {
            message.setStatus(VoiceCallMessage.CallStatus.REJECTED);
            log.error("语音通话请求处理失败: {} -> {}, 错误: {}", caller, receiver, e.getMessage());
        }
        
        return message;
    }
    
    /**
     * 处理通话接受
     */
    @MessageMapping("/voice/call/accept")
    @SendToUser("/queue/voice/call")
    public VoiceCallMessage handleCallAccept(VoiceCallMessage message, Principal principal) {
        String receiver = principal.getName();
        String callId = message.getCallId();
        
        try {
            voiceCallService.acceptCall(callId, receiver);
            message.setStatus(VoiceCallMessage.CallStatus.IN_PROGRESS);
            log.info("通话接受处理成功: {}, 通话ID: {}", receiver, callId);
        } catch (Exception e) {
            message.setStatus(VoiceCallMessage.CallStatus.ENDED);
            log.error("通话接受处理失败: {}, 通话ID: {}, 错误: {}", receiver, callId, e.getMessage());
        }
        
        return message;
    }
    
    /**
     * 处理通话拒绝
     */
    @MessageMapping("/voice/call/reject")
    @SendToUser("/queue/voice/call")
    public VoiceCallMessage handleCallReject(VoiceCallMessage message, Principal principal) {
        String receiver = principal.getName();
        String callId = message.getCallId();
        
        try {
            voiceCallService.rejectCall(callId, receiver);
            message.setStatus(VoiceCallMessage.CallStatus.REJECTED);
            log.info("通话拒绝处理成功: {}, 通话ID: {}", receiver, callId);
        } catch (Exception e) {
            message.setStatus(VoiceCallMessage.CallStatus.ENDED);
            log.error("通话拒绝处理失败: {}, 通话ID: {}, 错误: {}", receiver, callId, e.getMessage());
        }
        
        return message;
    }
    
    /**
     * 处理通话结束
     */
    @MessageMapping("/voice/call/end")
    @SendToUser("/queue/voice/call")
    public VoiceCallMessage handleCallEnd(VoiceCallMessage message, Principal principal) {
        String username = principal.getName();
        String callId = message.getCallId();
        
        try {
            voiceCallService.endCall(callId, username);
            message.setStatus(VoiceCallMessage.CallStatus.ENDED);
            log.info("通话结束处理成功: {}, 通话ID: {}", username, callId);
        } catch (Exception e) {
            log.error("通话结束处理失败: {}, 通话ID: {}, 错误: {}", username, callId, e.getMessage());
        }
        
        return message;
    }
    
    /**
     * 处理音频数据传输
     */
    @MessageMapping("/voice/audio/send")
    @SendToUser("/queue/voice/audio")
    public VoiceCallMessage handleAudioData(VoiceCallMessage message, Principal principal) {
        String username = principal.getName();
        String callId = message.getCallId();
        String audioData = message.getAudioData();
        
        try {
            voiceCallService.sendAudioData(callId, username, audioData);
            log.debug("音频数据传输成功: {}, 通话ID: {}, 数据长度: {}", username, callId, 
                    audioData != null ? audioData.length() : 0);
        } catch (Exception e) {
            log.error("音频数据传输失败: {}, 通话ID: {}, 错误: {}", username, callId, e.getMessage());
        }
        
        return message;
    }
    
    /**
     * 处理ICE候选传输
     */
    @MessageMapping("/voice/ice/send")
    @SendToUser("/queue/voice/ice")
    public VoiceCallMessage handleIceCandidate(VoiceCallMessage message, Principal principal) {
        String username = principal.getName();
        String callId = message.getCallId();
        String iceCandidate = message.getAudioData();
        
        try {
            voiceCallService.sendIceCandidate(callId, username, iceCandidate);
            log.debug("ICE候选传输成功: {}, 通话ID: {}", username, callId);
        } catch (Exception e) {
            log.error("ICE候选传输失败: {}, 通话ID: {}, 错误: {}", username, callId, e.getMessage());
        }
        
        return message;
    }
    
    /**
     * 处理SDP Offer传输
     */
    @MessageMapping("/voice/sdp/offer")
    @SendToUser("/queue/voice/sdp")
    public VoiceCallMessage handleSdpOffer(VoiceCallMessage message, Principal principal) {
        String username = principal.getName();
        String callId = message.getCallId();
        String sdpOffer = message.getAudioData();
        
        try {
            voiceCallService.sendSdpOffer(callId, username, sdpOffer);
            log.debug("SDP Offer传输成功: {}, 通话ID: {}", username, callId);
        } catch (Exception e) {
            log.error("SDP Offer传输失败: {}, 通话ID: {}, 错误: {}", username, callId, e.getMessage());
        }
        
        return message;
    }
    
    /**
     * 处理SDP Answer传输
     */
    @MessageMapping("/voice/sdp/answer")
    @SendToUser("/queue/voice/sdp")
    public VoiceCallMessage handleSdpAnswer(VoiceCallMessage message, Principal principal) {
        String username = principal.getName();
        String callId = message.getCallId();
        String sdpAnswer = message.getAudioData();
        
        try {
            voiceCallService.sendSdpAnswer(callId, username, sdpAnswer);
            log.debug("SDP Answer传输成功: {}, 通话ID: {}", username, callId);
        } catch (Exception e) {
            log.error("SDP Answer传输失败: {}, 通话ID: {}, 错误: {}", username, callId, e.getMessage());
        }
        
        return message;
    }
}