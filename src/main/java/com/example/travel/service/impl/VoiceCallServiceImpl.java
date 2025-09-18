package com.example.travel.service.impl;

import com.example.travel.dto.VoiceCallMessage;
import com.example.travel.service.VoiceCallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 语音通话服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VoiceCallServiceImpl implements VoiceCallService {
    
    private static final Logger log = LoggerFactory.getLogger(VoiceCallServiceImpl.class);
    
    private final SimpMessagingTemplate messagingTemplate;
    
    // 存储活跃的通话信息
    private final Map<String, Map<String, Object>> activeCalls = new ConcurrentHashMap<>();
    
    // 存储用户与通话的映射关系
    private final Map<String, String> userCallMap = new ConcurrentHashMap<>();
    
    @Override
    public String initiateCall(String caller, String receiver) {
        // 检查用户是否已经在通话中
        if (isUserInCall(caller) || isUserInCall(receiver)) {
            throw new RuntimeException("用户正在通话中");
        }
        
        String callId = UUID.randomUUID().toString();
        
        // 创建通话信息
        Map<String, Object> callInfo = new ConcurrentHashMap<>();
        callInfo.put("caller", caller);
        callInfo.put("receiver", receiver);
        callInfo.put("status", VoiceCallMessage.CallStatus.RINGING);
        callInfo.put("startTime", LocalDateTime.now());
        callInfo.put("participants", new String[]{caller, receiver});
        
        activeCalls.put(callId, callInfo);
        userCallMap.put(caller, callId);
        userCallMap.put(receiver, callId);
        
        // 发送通话请求
        VoiceCallMessage callRequest = new VoiceCallMessage(
            VoiceCallMessage.CallType.CALL_REQUEST,
            caller,
            receiver,
            callId
        );
        
        messagingTemplate.convertAndSendToUser(
            receiver,
            "/queue/voice/call",
            callRequest
        );
        
        log.info("用户 {} 向 {} 发起语音通话，通话ID: {}", caller, receiver, callId);
        return callId;
    }
    
    @Override
    public void acceptCall(String callId, String receiver) {
        Map<String, Object> callInfo = activeCalls.get(callId);
        if (callInfo == null) {
            throw new RuntimeException("通话不存在");
        }
        
        String caller = (String) callInfo.get("caller");
        if (!receiver.equals(callInfo.get("receiver"))) {
            throw new RuntimeException("无权接受此通话");
        }
        
        // 更新通话状态
        callInfo.put("status", VoiceCallMessage.CallStatus.IN_PROGRESS);
        
        // 发送通话接受消息
        VoiceCallMessage callAccept = new VoiceCallMessage(
            VoiceCallMessage.CallType.CALL_ACCEPT,
            receiver,
            caller,
            callId
        );
        
        messagingTemplate.convertAndSendToUser(
            caller,
            "/queue/voice/call",
            callAccept
        );
        
        log.info("用户 {} 接受了来自 {} 的通话，通话ID: {}", receiver, caller, callId);
    }
    
    @Override
    public void rejectCall(String callId, String receiver) {
        Map<String, Object> callInfo = activeCalls.get(callId);
        if (callInfo == null) {
            throw new RuntimeException("通话不存在");
        }
        
        String caller = (String) callInfo.get("caller");
        
        // 发送通话拒绝消息
        VoiceCallMessage callReject = new VoiceCallMessage(
            VoiceCallMessage.CallType.CALL_REJECT,
            receiver,
            caller,
            callId
        );
        
        messagingTemplate.convertAndSendToUser(
            caller,
            "/queue/voice/call",
            callReject
        );
        
        // 清理通话信息
        cleanupCall(callId);
        
        log.info("用户 {} 拒绝了来自 {} 的通话，通话ID: {}", receiver, caller, callId);
    }
    
    @Override
    public void endCall(String callId, String username) {
        Map<String, Object> callInfo = activeCalls.get(callId);
        if (callInfo == null) {
            throw new RuntimeException("通话不存在");
        }
        
        String caller = (String) callInfo.get("caller");
        String receiver = (String) callInfo.get("receiver");
        
        // 发送通话结束消息
        VoiceCallMessage callEnd = new VoiceCallMessage(
            VoiceCallMessage.CallType.CALL_END,
            username,
            username.equals(caller) ? receiver : caller,
            callId
        );
        
        messagingTemplate.convertAndSendToUser(
            username.equals(caller) ? receiver : caller,
            "/queue/voice/call",
            callEnd
        );
        
        // 清理通话信息
        cleanupCall(callId);
        
        log.info("用户 {} 结束了通话，通话ID: {}", username, callId);
    }
    
    @Override
    public void sendAudioData(String callId, String username, String audioData) {
        Map<String, Object> callInfo = activeCalls.get(callId);
        if (callInfo == null) {
            throw new RuntimeException("通话不存在");
        }
        
        String caller = (String) callInfo.get("caller");
        String receiver = (String) callInfo.get("receiver");
        String targetUser = username.equals(caller) ? receiver : caller;
        
        // 发送音频数据
        VoiceCallMessage audioMessage = new VoiceCallMessage(
            VoiceCallMessage.CallType.AUDIO_DATA,
            username,
            targetUser,
            callId,
            audioData
        );
        
        messagingTemplate.convertAndSendToUser(
            targetUser,
            "/queue/voice/audio",
            audioMessage
        );
    }
    
    @Override
    public void sendIceCandidate(String callId, String username, String iceCandidate) {
        Map<String, Object> callInfo = activeCalls.get(callId);
        if (callInfo == null) {
            throw new RuntimeException("通话不存在");
        }
        
        String caller = (String) callInfo.get("caller");
        String receiver = (String) callInfo.get("receiver");
        String targetUser = username.equals(caller) ? receiver : caller;
        
        // 发送ICE候选
        VoiceCallMessage iceMessage = new VoiceCallMessage(
            VoiceCallMessage.CallType.ICE_CANDIDATE,
            username,
            targetUser,
            callId
        );
        iceMessage.setAudioData(iceCandidate);
        
        messagingTemplate.convertAndSendToUser(
            targetUser,
            "/queue/voice/ice",
            iceMessage
        );
    }
    
    @Override
    public void sendSdpOffer(String callId, String username, String sdpOffer) {
        Map<String, Object> callInfo = activeCalls.get(callId);
        if (callInfo == null) {
            throw new RuntimeException("通话不存在");
        }
        
        String caller = (String) callInfo.get("caller");
        String receiver = (String) callInfo.get("receiver");
        String targetUser = username.equals(caller) ? receiver : caller;
        
        // 发送SDP Offer
        VoiceCallMessage sdpMessage = new VoiceCallMessage(
            VoiceCallMessage.CallType.SDP_OFFER,
            username,
            targetUser,
            callId
        );
        sdpMessage.setAudioData(sdpOffer);
        
        messagingTemplate.convertAndSendToUser(
            targetUser,
            "/queue/voice/sdp",
            sdpMessage
        );
    }
    
    @Override
    public void sendSdpAnswer(String callId, String username, String sdpAnswer) {
        Map<String, Object> callInfo = activeCalls.get(callId);
        if (callInfo == null) {
            throw new RuntimeException("通话不存在");
        }
        
        String caller = (String) callInfo.get("caller");
        String receiver = (String) callInfo.get("receiver");
        String targetUser = username.equals(caller) ? receiver : caller;
        
        // 发送SDP Answer
        VoiceCallMessage sdpMessage = new VoiceCallMessage(
            VoiceCallMessage.CallType.SDP_ANSWER,
            username,
            targetUser,
            callId
        );
        sdpMessage.setAudioData(sdpAnswer);
        
        messagingTemplate.convertAndSendToUser(
            targetUser,
            "/queue/voice/sdp",
            sdpMessage
        );
    }
    
    @Override
    public Map<String, Object> getActiveCall(String callId) {
        return activeCalls.get(callId);
    }
    
    @Override
    public boolean isUserInCall(String username) {
        return userCallMap.containsKey(username);
    }
    
    @Override
    @Scheduled(fixedRate = 60000) // 每分钟清理一次过期通话
    public void cleanupExpiredCalls() {
        LocalDateTime now = LocalDateTime.now();
        activeCalls.entrySet().removeIf(entry -> {
            Map<String, Object> callInfo = entry.getValue();
            LocalDateTime startTime = (LocalDateTime) callInfo.get("startTime");
            if (startTime.plusMinutes(30).isBefore(now)) {
                // 清理用户映射
                String caller = (String) callInfo.get("caller");
                String receiver = (String) callInfo.get("receiver");
                userCallMap.remove(caller);
                userCallMap.remove(receiver);
                return true;
            }
            return false;
        });
    }
    
    /**
     * 清理通话信息
     */
    private void cleanupCall(String callId) {
        Map<String, Object> callInfo = activeCalls.remove(callId);
        if (callInfo != null) {
            String caller = (String) callInfo.get("caller");
            String receiver = (String) callInfo.get("receiver");
            userCallMap.remove(caller);
            userCallMap.remove(receiver);
        }
    }
}