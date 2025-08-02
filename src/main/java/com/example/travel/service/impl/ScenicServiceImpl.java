package com.example.travel.service.impl;

import com.example.travel.dao.ScenicDao;
import com.example.travel.entity.Scenic;
import com.example.travel.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScenicServiceImpl implements ScenicService {
    @Autowired
    private ScenicDao scenicDao;


    @Override
    public List<Scenic> getScenic(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        return scenicDao.findAllScenic(offset, pageSize, keyword);
    }

    @Override
    public int countScenic(String keyword) {
        return scenicDao.countScenic(keyword);
    }
    //新增
    @Override
    public void addScenic(Scenic scenic) {
        scenicDao.insertScenic(scenic);
    }

    @Override
    public boolean existById(Long id) {
        return scenicDao.exists(id);
    }
    //删除
    @Override
    public void deleteScenic(Long id) {
        scenicDao.deleteScenic(id);
    }

    @Override
    public Scenic getById(Long id) {
        return scenicDao.findById(id);
    }

    //更新
    @Override
    public void updateScenic(Scenic scenic) {
        scenic.setUpdated_at(LocalDateTime.now());
        scenicDao.updateScenic(scenic);
    }
}
