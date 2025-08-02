package com.example.travel.service.impl;

import com.example.travel.dao.TravelNoteDao;
import com.example.travel.entity.TravelNote;
import com.example.travel.service.TravelNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TravelNoteServiceImpl implements TravelNoteService {
    @Autowired
    private TravelNoteDao travelNoteDao;
    @Override
    public TravelNote getNoteById(String id) {
        return travelNoteDao.findById(id);
    }

    @Override
    public List<TravelNote> getAllNotes(String keyword, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return travelNoteDao.findAll(keyword, offset, pageSize);
    }

    @Override
    public List<TravelNote> searchNotesByTitle(int page, int pageSize,String keyword,String username) {
        int offset = (page - 1) * pageSize;
        return travelNoteDao.findByTitleContaining(keyword, username, offset, pageSize);
    }


    @Override
    public int countAllNotes(String username ,String keyword) {
        return travelNoteDao.countAllNote(username , keyword);
    }

    @Override
    public int countNotesByTitle(String keyword) {
        return travelNoteDao.countByTitleContaining(keyword);
    }

    @Override
    public int updateNote(TravelNote note) {
        return travelNoteDao.updateNote(note);
    }
    
    @Override
    public int countNotes(String keyword) {
        return travelNoteDao.countNotes(keyword);
    }

    @Override
    public int insertNote(TravelNote note) {
        return travelNoteDao.insertNote(note);
    }

    @Override
    public int deleteNote(String id) {
        return travelNoteDao.deleteById(id);
    }

}
