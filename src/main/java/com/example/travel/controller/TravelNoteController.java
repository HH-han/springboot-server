package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.TravelNote;
import com.example.travel.service.TravelNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public/notes")
public class TravelNoteController {
    @Autowired
    private TravelNoteService travelNoteService;
    @GetMapping("/{id}")
    public Result getNoteById(@PathVariable String id) {
        travelNoteService.getNoteById(id);
        return Result.success(travelNoteService.getNoteById(id));
    }

    // 获取所有
    @GetMapping("/all")
    public Result getAllNotes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        List<TravelNote> travelNotes=travelNoteService.getAllNotes(keyword,page, pageSize);
        int total=travelNoteService.countNotes(keyword);
        Map<String ,Object> result=new HashMap<>();
        result.put("list", travelNotes);
        result.put("total", total);
        return Result.success(result);
    }

    @GetMapping("/search")
    public Result findAllNote(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam String username) {
        List<TravelNote> travelNotes=travelNoteService.searchNotesByTitle(page, pageSize, keyword, username);
        int total=travelNoteService.countAllNotes(username, keyword);
        Map<String ,Object> result=new HashMap<>();
        result.put("list", travelNotes);
        result.put("total", total);
        return Result.success(result);
    }

    @GetMapping("/count")
    public int countAllNotes(@RequestParam String username ,@RequestParam(required = false, defaultValue = "") String keyword) {
        return travelNoteService.countAllNotes(username,keyword);
    }

    @GetMapping("/count/search")
    public Result countNotesByTitle(@RequestParam(required = false, defaultValue = "") String keyword) {
        travelNoteService.countNotesByTitle(keyword);
        return Result.success(travelNoteService.countNotesByTitle(keyword));
    }

    @PutMapping
    public Result updateNote(@RequestBody TravelNote note) {
        travelNoteService.updateNote(note);
        return  Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deleteNote(@PathVariable String id) {
        travelNoteService.deleteNote(id);
        return Result.success();
    }
    // 新增
    @PostMapping("/insert")
    public Result insertNote(@RequestBody TravelNote note) {
        travelNoteService.insertNote(note);
        return Result.success(note);
    }
}
