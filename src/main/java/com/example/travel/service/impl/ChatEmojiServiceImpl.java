package com.example.travel.service.impl;

import com.example.travel.dao.ChatEmojiDao;
import com.example.travel.entity.ChatEmoji;
import com.example.travel.service.ChatEmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 聊天表情服务实现类
 */
@Service
public class ChatEmojiServiceImpl implements ChatEmojiService {

    @Autowired
    private ChatEmojiDao chatEmojiDao;

    @Override
    public List<ChatEmoji> findAll() {
        return chatEmojiDao.findAll();
    }

    @Override
    public ChatEmoji findById(Integer id) {
        return chatEmojiDao.findById(id);
    }

    @Override
    public ChatEmoji findByEmojiCode(String emojiCode) {
        return chatEmojiDao.findByEmojiCode(emojiCode);
    }

    @Override
    public List<ChatEmoji> findByType(String type) {
        return chatEmojiDao.findByType(type);
    }

    @Override
    public List<ChatEmoji> findAllEmojis(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        return chatEmojiDao.findAllEmojis(offset, pageSize, keyword);
    }

    @Override
    public int countEmojis(String keyword) {
        return chatEmojiDao.countEmojis(keyword);
    }

    @Override
    public int insert(ChatEmoji chatEmoji) {
        return chatEmojiDao.insert(chatEmoji);
    }

    @Override
    public int update(ChatEmoji chatEmoji) {
        return chatEmojiDao.update(chatEmoji);
    }

    @Override
    public int delete(Integer id) {
        return chatEmojiDao.delete(id);
    }

    @Override
    public boolean exists(Integer id) {
        return chatEmojiDao.exists(id);
    }
}