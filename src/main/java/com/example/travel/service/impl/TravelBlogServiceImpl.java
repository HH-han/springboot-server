package com.example.travel.service.impl;

import com.example.travel.dao.TravelBlogDao;
import com.example.travel.entity.TravelBlog;
import com.example.travel.service.TravelBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TravelBlogServiceImpl implements TravelBlogService {

    @Autowired
    private TravelBlogDao travelBlogDao;


    @Override
    public List<TravelBlog> getTravel(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        return travelBlogDao.findAllTravel(offset, pageSize, keyword);
    }

    @Override
    public int countTravel(String keyword) {

        return travelBlogDao.countTravel(keyword);
    }

    @Override
    public Optional<TravelBlog> getBlogById(Long id) {
        return Optional.empty();
    }

    @Override
    public void likeBlog(Long id) {

        travelBlogDao.incrementLikes(id);
    }

    @Override
    public void favoriteBlog(Long id) {

        travelBlogDao.incrementFavorites(id);
    }

    @Override
    public void saveBlog(TravelBlog blog) {
        if (blog.getTitle() == null || blog.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("游记标题不能为空");
        }
        if (blog.getContent() == null || blog.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("游记内容不能为空");
        }
        blog.setCreated_at(LocalDateTime.now());
        travelBlogDao.insertTravelBlog(blog);
    }

    @Override
    public void updateBlog(TravelBlog blog) {
        if (blog.getTitle() != null && blog.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("游记标题不能为空");
        }
        if (blog.getContent() != null && blog.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("游记内容不能为空");
        }
        blog.setUpdatedAt(LocalDateTime.now());
        travelBlogDao.updateTravelBlog(blog);
    }

    @Override
    public void deleteBlog(Long id) {

        travelBlogDao.deleteTravelBlog(id);
    }

    @Override
    public boolean existById(Long id) {
        return
                travelBlogDao.exists(id);
    }

    @Override
    public TravelBlog getById(Long id) {
        return travelBlogDao.findById(id);
    }
}