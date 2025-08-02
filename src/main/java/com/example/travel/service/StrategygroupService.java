package com.example.travel.service;

import com.example.travel.entity.Strategygroup;
import java.util.List;

public interface StrategygroupService {
    Strategygroup createStrategygroup(Strategygroup strategygroup);
    
    Strategygroup getStrategygroupById(Integer id);
    
    List<Strategygroup> getAllStrategygroups(int offset, int pageSize);
    
    List<Strategygroup> searchStrategygroupsByTitle(String title);
    
    Strategygroup updateStrategygroup(Strategygroup strategygroup);
    
    void deleteStrategygroup(Integer id);
    
    long countStrategygroups();
    
    long countStrategygroupsByTitle(String title);
}
