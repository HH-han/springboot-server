package com.example.travel.service;

import com.example.travel.entity.TravelNote;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TravelNoteService {
    TravelNote getNoteById(String id);
    
    List<TravelNote> getAllNotes(String keyword, int page, int pageSize);
    
    List<TravelNote> searchNotesByTitle( int page, int pageSize ,String keyword,String username);
    
    int countAllNotes(String username, String keyword);
    
    int countNotesByTitle(String keyword);
    
    int updateNote(TravelNote note);
    
    int deleteNote(String id);

    int countNotes(String keyword);

    int insertNote(TravelNote note);
}
