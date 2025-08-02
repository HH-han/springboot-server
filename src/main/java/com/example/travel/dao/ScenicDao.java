package com.example.travel.dao;

import com.example.travel.entity.Scenic;
import com.example.travel.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScenicDao {
    //List<Scenic> findAllCards();
    //查询所有景点
    List<Scenic> findAllScenic(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("keyword") String keyword);
    //查询订单总数
    int countScenic(@Param("keyword") String keyword);
    //新增
    int insertScenic(Scenic scenic);
    //修改
    int updateScenic(Scenic scenic);
    //删除
    boolean exists(Long id);
    void deleteScenic(Long id);
    //根据id查询
    Scenic findById(Long id);
}
