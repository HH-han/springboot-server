package com.example.travel.service.impl;

import com.example.travel.dao.TravelPostDao;
import com.example.travel.entity.TravelPost;
import com.example.travel.service.TravelPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TravelPostServiceImpl implements TravelPostService {

    private final TravelPostDao travelPostDao;

    @Autowired
    public TravelPostServiceImpl(TravelPostDao travelPostDao) {
        this.travelPostDao = travelPostDao;
    }

    @Override
    public List<TravelPost> getfindAllPosts(int page, int pageSize, String keyword, String username) {
        int offset = (page - 1) * pageSize;
        if (keyword != null && !keyword.isEmpty()) {
            return travelPostDao.findByTitleContaining(keyword, offset, username, pageSize);
        }
        return travelPostDao.findAll(offset, pageSize, username);
    }

    @Override
    public List<TravelPost> getAllPost(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        if (keyword != null && !keyword.isEmpty()) {
            return travelPostDao.findAllByTitleContaining(keyword, offset, pageSize);
        }
        return travelPostDao.findAllPost(offset, pageSize);
    }

    @Override
    public int countPosts(String keyword, String username) {
        if (keyword != null && !keyword.isEmpty()) {
            return travelPostDao.countByTitleContaining(keyword);
        }
        return travelPostDao.countAll(keyword);
    }
    @Override
    public int countAllPosts(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return travelPostDao.countByTitleContaining(keyword);
        }
        return travelPostDao.countAll(keyword);
    }
    @Override
    public TravelPost getPostById(Long id) {

        return travelPostDao.findById(id);
    }

    @Override
    @Transactional
    public TravelPost createPost(TravelPost post) {
        // 设置创建和更新时间
        post.setCreatedAt(new Date());
        post.setUpdatedAt(new Date());
        
        // 插入帖子
        travelPostDao.insertPost(post);
        
        return getPostById(post.getId());
    }

    @Override
    @Transactional
    public TravelPost updatePost(Long id, TravelPost postDetails) {
        TravelPost existingPost = getPostById(id);

        // 更新基本信息
        existingPost.setTitle(postDetails.getTitle());
        existingPost.setLocation(postDetails.getLocation());
        existingPost.setDate(postDetails.getDate());
        existingPost.setContent(postDetails.getContent());
        existingPost.setImages(postDetails.getImages());
        existingPost.setTags(postDetails.getTags());
        existingPost.setUpdatedAt(new Date());

        travelPostDao.updatePost(existingPost);

        return getPostById(id);
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        // 删除主表数据
        travelPostDao.deleteById(id);
    }

    @Override
    public List<TravelPost> searchByLocation(String location) {
        return travelPostDao.findByLocation(location);
    }

    @Override
    public List<TravelPost> searchByTag(String tag) {
        return travelPostDao.findByTag(tag);
    }

    @Override
    public List<TravelPost> searchByKeyword(String keyword) {
        return travelPostDao.findByTitleContaining(keyword);
    }
}