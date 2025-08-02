package com.example.travel.dao;

import com.example.travel.entity.Destination;
import jakarta.persistence.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Table(name = "destination")
@Mapper
public interface DestinationDao {
    List<Destination> findBySearchAndCategory(
            @Param("searchQuery") String searchQuery,
            @Param("category") String category,
            @Param("offset") int offset,
            @Param("pageSize") int pageSize);
    int countBySearchAndCategory(
            @Param("searchQuery") String searchQuery,
            @Param("category") String category);
    //新增
    int insertDestination(Destination destination);
    //修改
    int updateDestination(Destination destination);
    //删除
    int deleteDestination(@Param("id") Long id);
    boolean exists(@Param("id") Long id);

    Destination findById(@Param("id") Long id);
}