package com.example.travel.service.impl;

import com.example.travel.dao.ChatGroupDao;
import com.example.travel.entity.ChatGroup;
import com.example.travel.service.ChatGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 聊天群组服务实现类
 */
@Service
@RequiredArgsConstructor
public class ChatGroupServiceImpl implements ChatGroupService {
    
    private final ChatGroupDao chatGroupDao;
    
    @Override
    public ChatGroup findById(Long id) {
        return chatGroupDao.findById(id);
    }
    
    @Override
    public List<ChatGroup> findByCreatorId(Long creatorId) {
        return chatGroupDao.findByCreatorId(creatorId);
    }
    
    @Override
    public List<ChatGroup> findByNameLike(String name) {
        return chatGroupDao.findByNameLike(name);
    }
    
    @Override
    public List<ChatGroup> findAll() {
        return chatGroupDao.findAll();
    }
    
    @Override
    @Transactional
    public int createGroup(ChatGroup chatGroup) {
        return chatGroupDao.insert(chatGroup);
    }
    
    @Override
    @Transactional
    public int updateGroup(ChatGroup chatGroup) {
        return chatGroupDao.update(chatGroup);
    }
    
    @Override
    @Transactional
    public int updateStatus(Long id, Integer status) {
        return chatGroupDao.updateStatus(id, status);
    }
    
    @Override
    @Transactional
    public int deleteGroup(Long id) {
        return chatGroupDao.delete(id);
    }
    
    @Override
    public int count() {
        return chatGroupDao.count();
    }
}