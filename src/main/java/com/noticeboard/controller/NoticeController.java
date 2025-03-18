package com.noticeboard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noticeboard.model.Notice;
import com.noticeboard.service.NoticeService;

@RestController
@RequestMapping ("/notice")
public class NoticeController {
    
    @Autowired
    private NoticeService noticeService;

    @GetMapping
    public ResponseEntity<List<Notice>> getAllNotices(){
        return ResponseEntity.ok(noticeService.getAllNotices());
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Notice>> getNoticeById(@PathVariable Long id){
        Optional<Notice> notice =noticeService.getNoticeById(id);
        return notice.isPresent() ? ResponseEntity.ok(notice) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Notice> createNotice(@RequestBody Notice notice){
        Notice createNotice = noticeService.createNotice(notice);
        return new ResponseEntity<>(createNotice, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if(noticeService.getNoticeById(id).isPresent()){
            noticeService.deleteNoticeById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
