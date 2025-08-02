package com.example.travel.dao;

import com.example.travel.entity.TravelNote;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface TravelNoteDao {
    // 新增
    int insertNote(TravelNote note);
    
    TravelNote findById(String id);
    
    List<TravelNote> findAll(
            @Param("keyword") String keyword,
            @Param("offset") int offset,
            @Param("pageSize") int pageSize
    );
    
    List<TravelNote> findByTitleContaining(
            @Param("keyword") String keyword,
            @Param("username") String username,
            @Param("offset") int offset,
            @Param("pageSize") int pageSize
    );
    
    int countAllNote(@Param("username") String username, @Param("keyword") String keyword);

    int countAll();

    int countByTitleContaining(@Param("keyword") String keyword);
    
    int updateNote(TravelNote note);
    
    int deleteById(String id);

    int countNotes(String keyword);
}
