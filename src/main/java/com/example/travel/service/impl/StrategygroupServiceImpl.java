package com.example.travel.service.impl;

import com.example.travel.dao.StrategygroupDao;
import com.example.travel.entity.Strategygroup;
import com.example.travel.service.StrategygroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StrategygroupServiceImpl implements StrategygroupService {
    
    @Autowired
    private StrategygroupDao strategygroupDao;
    
    @Override
    public Strategygroup createStrategygroup(Strategygroup strategygroup) {
        strategygroupDao.insertStrategygroup(strategygroup);
        return strategygroup;
    }
    
    @Override
    public Strategygroup getStrategygroupById(Integer id) {
        return strategygroupDao.findById(id);
    }
    
    @Override
    public List<Strategygroup> getAllStrategygroups(int offset, int pageSize) {
        return strategygroupDao.findAll(offset, pageSize);
    }
    
    @Override
    public List<Strategygroup> searchStrategygroupsByTitle(String title) {
        return strategygroupDao.findByTitleContaining(title);
    }
    
    @Override
    public Strategygroup updateStrategygroup(Strategygroup strategygroup) {
        strategygroupDao.updateStrategygroup(strategygroup);
        return strategygroup;
    }
    
    @Override
    public void deleteStrategygroup(Integer id) {
        strategygroupDao.deleteById(id);
    }
    
    @Override
    public long countStrategygroups() {
        return strategygroupDao.countAll();
    }
    
    @Override
    public long countStrategygroupsByTitle(String title) {
        return strategygroupDao.countByTitleContaining(title);
    }
}
