package com.example.travel.dao;
import com.example.travel.entity.TravelWorldcharacteristics;
import jakarta.persistence.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Table(name = "travel_worldcharacteristics")
@Mapper
public interface TravelWorldcharacteristicsDao {
    //查询
    List<TravelWorldcharacteristics> findBySearchAndCategory(
            @Param("searchQuery") String searchQuery,
            @Param("category") String category,
            @Param("offset") int offset,
            @Param("pageSize") int pageSize);
    //查询总条数
    int countBySearchAndCategory(
            @Param("searchQuery") String searchQuery,
            @Param("category") String category);
    //新增
    int insert(TravelWorldcharacteristics travelWorldcharacteristics);
    //修改
    int update(TravelWorldcharacteristics travelWorldcharacteristics);
    //删除
    int delete(@Param("id") Long id);
    boolean exists(@Param("id") Long id);
    //查询
    TravelWorldcharacteristics findById(@Param("id") Long id);
}