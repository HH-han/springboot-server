package com.example.travel.service.impl;

import com.example.travel.dao.TravelCollectionDao;
import com.example.travel.entity.TravelCollection;
import com.example.travel.service.TravelCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelCollectionServiceImpl implements TravelCollectionService {

    @Autowired
    private TravelCollectionDao travelCollectionDao;

    @Override
    public List<TravelCollection> findAll() {
        return travelCollectionDao.findAll();
    }

    @Override
    public TravelCollection findById(Integer id) {
        return travelCollectionDao.findById(id);
    }

    @Override
    public List<TravelCollection> findByUsername(String username) {
        return travelCollectionDao.findByUsername(username);
    }

    @Override
    public List<TravelCollection> findAllCollections(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        return travelCollectionDao.findAllCollections(offset, pageSize, keyword);
    }

    @Override
    public int countCollection(String keyword) {
        return travelCollectionDao.countCollection(keyword);
    }

    @Override
    public int insertCollection(TravelCollection travelCollection) {
        return travelCollectionDao.insertCollection(travelCollection);
    }

    @Override
    public int updateCollection(TravelCollection travelCollection) {
        return travelCollectionDao.updateCollection(travelCollection);
    }

    @Override
    public int deleteCollection(Integer id) {
        return travelCollectionDao.deleteCollection(id);
    }

    @Override
    public List<TravelCollection> searchCollections(Integer id, String username, String collectionname, String location, String classification) {
        return travelCollectionDao.searchCollections(id, username, collectionname, location, classification);
    }
}