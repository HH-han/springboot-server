package com.example.travel.controller;

import com.example.travel.dto.VoiceCallMessage;
import com.example.travel.service.VoiceCallService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Map;

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
    private final SimpMessagingTemplate messagingTemplate;
    
    /**
     * 处理语音通话请求
     */
    @MessageMapping("/voice/call/request")
    public void handleCallRequest(VoiceCallMessage message, Principal principal) {
        String caller = principal.getName();
        String receiver = message.getReceiver();
        
        try {
            String callId = voiceCallService.initiateCall(caller, receiver);
            message.setCallId(callId);
            message.setStatus(VoiceCallMessage.CallStatus.RINGING);
            
            // 向发起者发送通话请求响应
            VoiceCallMessage callerResponse = new VoiceCallMessage(
                VoiceCallMessage.CallType.CALL_REQUEST,
                caller,
                receiver,
                callId,
                null,
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.RINGING
            );
            messagingTemplate.convertAndSendToUser(caller, "/queue/voice/call", callerResponse);
            
            // 向接收者发送通话请求
            VoiceCallMessage receiverMessage = new VoiceCallMessage(
                VoiceCallMessage.CallType.CALL_REQUEST,
                caller,
                receiver,
                callId,
                null,
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.RINGING
            );
            messagingTemplate.convertAndSendToUser(receiver, "/queue/voice/call", receiverMessage);
            
            log.info("语音通话请求处理成功: {} -> {}, 通话ID: {}", caller, receiver, callId);
        } catch (Exception e) {
            // 向发起者发送错误响应
            VoiceCallMessage errorResponse = new VoiceCallMessage(
                VoiceCallMessage.CallType.CALL_REQUEST,
                caller,
                receiver,
                null,
                null,
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.REJECTED
            );
            messagingTemplate.convertAndSendToUser(caller, "/queue/voice/call", errorResponse);
            log.error("语音通话请求处理失败: {} -> {}, 错误: {}", caller, receiver, e.getMessage());
        }
    }
    
    /**
     * 处理通话接受
     */
    @MessageMapping("/voice/call/accept")
    public void handleCallAccept(VoiceCallMessage message, Principal principal) {
        String receiver = principal.getName();
        String callId = message.getCallId();
        
        try {
            // 获取通话信息
            Map<String, Object> callInfo = voiceCallService.getActiveCall(callId);
            String caller = (String) callInfo.get("caller");
            
            voiceCallService.acceptCall(callId, receiver);
            
            // 向接受者发送通话接受响应
            VoiceCallMessage acceptResponse = new VoiceCallMessage(
                VoiceCallMessage.CallType.CALL_ACCEPT,
                caller,
                receiver,
                callId,
                null,
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.IN_PROGRESS
            );
            messagingTemplate.convertAndSendToUser(receiver, "/queue/voice/call", acceptResponse);
            
            // 向发起者发送通话接受消息
            VoiceCallMessage callerMessage = new VoiceCallMessage(
                VoiceCallMessage.CallType.CALL_ACCEPT,
                caller,
                receiver,
                callId,
                null,
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.IN_PROGRESS
            );
            messagingTemplate.convertAndSendToUser(caller, "/queue/voice/call", callerMessage);
            
            log.info("通话接受处理成功: {}, 通话ID: {}", receiver, callId);
        } catch (Exception e) {
            // 向接受者发送错误响应
            VoiceCallMessage errorResponse = new VoiceCallMessage(
                VoiceCallMessage.CallType.CALL_ACCEPT,
                null,
                receiver,
                callId,
                null,
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.ENDED
            );
            messagingTemplate.convertAndSendToUser(receiver, "/queue/voice/call", errorResponse);
            log.error("通话接受处理失败: {}, 通话ID: {}, 错误: {}", receiver, callId, e.getMessage());
        }
    }
    
    /**
     * 处理通话拒绝
     */
    @MessageMapping("/voice/call/reject")
    public void handleCallReject(VoiceCallMessage message, Principal principal) {
        String receiver = principal.getName();
        String callId = message.getCallId();
        
        try {
            // 获取通话信息
            Map<String, Object> callInfo = voiceCallService.getActiveCall(callId);
            String caller = (String) callInfo.get("caller");
            
            voiceCallService.rejectCall(callId, receiver);
            
            // 向拒绝者发送通话拒绝响应
            VoiceCallMessage rejectResponse = new VoiceCallMessage(
                VoiceCallMessage.CallType.CALL_REJECT,
                caller,
                receiver,
                callId,
                null,
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.REJECTED
            );
            messagingTemplate.convertAndSendToUser(receiver, "/queue/voice/call", rejectResponse);
            
            // 向发起者发送通话拒绝消息
            VoiceCallMessage callerMessage = new VoiceCallMessage(
                VoiceCallMessage.CallType.CALL_REJECT,
                caller,
                receiver,
                callId,
                null,
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.REJECTED
            );
            messagingTemplate.convertAndSendToUser(caller, "/queue/voice/call", callerMessage);
            
            log.info("通话拒绝处理成功: {}, 通话ID: {}", receiver, callId);
        } catch (Exception e) {
            // 向拒绝者发送错误响应
            VoiceCallMessage errorResponse = new VoiceCallMessage(
                VoiceCallMessage.CallType.CALL_REJECT,
                null,
                receiver,
                callId,
                null,
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.ENDED
            );
            messagingTemplate.convertAndSendToUser(receiver, "/queue/voice/call", errorResponse);
            log.error("通话拒绝处理失败: {}, 通话ID: {}, 错误: {}", receiver, callId, e.getMessage());
        }
    }
    
    /**
     * 处理通话结束
     */
    @MessageMapping("/voice/call/end")
    public void handleCallEnd(VoiceCallMessage message, Principal principal) {
        String username = principal.getName();
        String callId = message.getCallId();
        
        try {
            // 获取通话信息
            Map<String, Object> callInfo = voiceCallService.getActiveCall(callId);
            String caller = (String) callInfo.get("caller");
            String receiver = (String) callInfo.get("receiver");
            
            voiceCallService.endCall(callId, username);
            
            // 向结束通话的用户发送响应
            VoiceCallMessage endResponse = new VoiceCallMessage(
                VoiceCallMessage.CallType.CALL_END,
                caller,
                receiver,
                callId,
                null,
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.ENDED
            );
            messagingTemplate.convertAndSendToUser(username, "/queue/voice/call", endResponse);
            
            // 向通话的另一方发送通话结束消息
            String otherParty = username.equals(caller) ? receiver : caller;
            VoiceCallMessage otherPartyMessage = new VoiceCallMessage(
                VoiceCallMessage.CallType.CALL_END,
                caller,
                receiver,
                callId,
                null,
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.ENDED
            );
            messagingTemplate.convertAndSendToUser(otherParty, "/queue/voice/call", otherPartyMessage);
            
            log.info("通话结束处理成功: {}, 通话ID: {}", username, callId);
        } catch (Exception e) {
            // 向结束通话的用户发送错误响应
            VoiceCallMessage errorResponse = new VoiceCallMessage(
                VoiceCallMessage.CallType.CALL_END,
                null,
                null,
                callId,
                null,
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.ENDED
            );
            messagingTemplate.convertAndSendToUser(username, "/queue/voice/call", errorResponse);
            log.error("通话结束处理失败: {}, 通话ID: {}, 错误: {}", username, callId, e.getMessage());
        }
    }
    
    /**
     * 处理音频数据传输
     */
    @MessageMapping("/voice/audio")
    public void handleAudioData(VoiceCallMessage message, Principal principal) {
        String username = principal.getName();
        String callId = message.getCallId();
        
        try {
            // 获取通话信息
            Map<String, Object> callInfo = voiceCallService.getActiveCall(callId);
            String caller = (String) callInfo.get("caller");
            String receiver = (String) callInfo.get("receiver");
            
            // 向通话的另一方转发音频数据
            String targetUser = username.equals(caller) ? receiver : caller;
            
            VoiceCallMessage forwardMessage = new VoiceCallMessage(
                VoiceCallMessage.CallType.AUDIO_DATA,
                caller,
                receiver,
                callId,
                message.getAudioData(),
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.IN_PROGRESS
            );
            
            messagingTemplate.convertAndSendToUser(targetUser, "/queue/voice/audio", forwardMessage);
            log.info("音频数据传输处理成功: {} -> {}, 通话ID: {}", username, targetUser, callId);
        } catch (Exception e) {
            log.error("音频数据传输处理失败: {}, 通话ID: {}, 错误: {}", username, callId, e.getMessage());
        }
    }
    
    /**
     * 处理ICE候选传输
     */
    @MessageMapping("/voice/ice")
    public void handleIceCandidate(VoiceCallMessage message, Principal principal) {
        String username = principal.getName();
        String callId = message.getCallId();
        
        try {
            // 获取通话信息
            Map<String, Object> callInfo = voiceCallService.getActiveCall(callId);
            String caller = (String) callInfo.get("caller");
            String receiver = (String) callInfo.get("receiver");
            
            // 向通话的另一方转发ICE候选
            String targetUser = username.equals(caller) ? receiver : caller;
            
            VoiceCallMessage forwardMessage = new VoiceCallMessage(
                VoiceCallMessage.CallType.ICE_CANDIDATE,
                caller,
                receiver,
                callId,
                message.getAudioData(), // ICE候选数据存储在audioData字段中
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.IN_PROGRESS
            );
            
            messagingTemplate.convertAndSendToUser(targetUser, "/queue/voice/ice", forwardMessage);
            log.info("ICE候选传输处理成功: {} -> {}, 通话ID: {}", username, targetUser, callId);
        } catch (Exception e) {
            log.error("ICE候选传输处理失败: {}, 通话ID: {}, 错误: {}", username, callId, e.getMessage());
        }
    }
    
    /**
     * 处理SDP Offer传输
     */
    @MessageMapping("/voice/sdp/offer")
    public void handleSdpOffer(VoiceCallMessage message, Principal principal) {
        String username = principal.getName();
        String callId = message.getCallId();
        
        try {
            // 获取通话信息
            Map<String, Object> callInfo = voiceCallService.getActiveCall(callId);
            String caller = (String) callInfo.get("caller");
            String receiver = (String) callInfo.get("receiver");
            
            // 向通话的另一方转发SDP Offer
            String targetUser = username.equals(caller) ? receiver : caller;
            
            VoiceCallMessage forwardMessage = new VoiceCallMessage(
                VoiceCallMessage.CallType.SDP_OFFER,
                caller,
                receiver,
                callId,
                message.getAudioData(), // SDP数据存储在audioData字段中
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.IN_PROGRESS
            );
            
            messagingTemplate.convertAndSendToUser(targetUser, "/queue/voice/sdp", forwardMessage);
            log.info("SDP Offer传输处理成功: {} -> {}, 通话ID: {}", username, targetUser, callId);
        } catch (Exception e) {
            log.error("SDP Offer传输处理失败: {}, 通话ID: {}, 错误: {}", username, callId, e.getMessage());
        }
    }
    
    /**
     * 处理SDP Answer传输
     */
    @MessageMapping("/voice/sdp/answer")
    public void handleSdpAnswer(VoiceCallMessage message, Principal principal) {
        String username = principal.getName();
        String callId = message.getCallId();
        
        try {
            // 获取通话信息
            Map<String, Object> callInfo = voiceCallService.getActiveCall(callId);
            String caller = (String) callInfo.get("caller");
            String receiver = (String) callInfo.get("receiver");
            
            // 向通话的另一方转发SDP Answer
            String targetUser = username.equals(caller) ? receiver : caller;
            
            VoiceCallMessage forwardMessage = new VoiceCallMessage(
                VoiceCallMessage.CallType.SDP_ANSWER,
                caller,
                receiver,
                callId,
                message.getAudioData(), // SDP数据存储在audioData字段中
                System.currentTimeMillis(),
                VoiceCallMessage.CallStatus.IN_PROGRESS
            );
            
            messagingTemplate.convertAndSendToUser(targetUser, "/queue/voice/sdp", forwardMessage);
            log.info("SDP Answer传输处理成功: {} -> {}, 通话ID: {}", username, targetUser, callId);
        } catch (Exception e) {
            log.error("SDP Answer传输处理失败: {}, 通话ID: {}, 错误: {}", username, callId, e.getMessage());
        }
    }
}