package com.example.travel.dao;

import com.example.travel.entity.Strategygroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StrategygroupDao {
    int insertStrategygroup(Strategygroup strategygroup);
    
    Strategygroup findById(@Param("id") Integer id);
    
    List<Strategygroup> findAll();
    
    List<Strategygroup> findByTitleContaining(@Param("title") String title);
    
    int updateStrategygroup(Strategygroup strategygroup);
    
    int deleteById(@Param("id") Integer id);

    int countAll();

    int countByTitleContaining(@Param("title") String title);

    List<Strategygroup> findAll(int offset, int pageSize);
}
