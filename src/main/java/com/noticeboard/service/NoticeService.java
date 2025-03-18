package com.noticeboard.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noticeboard.model.Notice;
import com.noticeboard.repository.NoticeRepository;



@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    // READ ALL
    public List<Notice> getAllNotices(){
        return noticeRepository.findAll();
    }

    // READ BY ID
    public Optional<Notice> getNoticeById(Long id){
        return noticeRepository.findById(id);
    }
    
    // DELETE
    public void deleteNoticeById(Long id){
        if (!noticeRepository.existsById(id)){
            throw new RuntimeException("Notice not found with ID "+id);
        }
        noticeRepository.deleteById(id);
    }
    
    // CREATE
    public Notice createNotice (Notice notice){
        notice.setPublicationDate(LocalDateTime.now());
        return noticeRepository.save(notice);
    }

    // UPDATED
    public Notice updatedNotice(Long id, Notice notice){
        if (!noticeRepository.existsById(id)){
            throw new RuntimeException("Notice not found with ID "+id);
        } 
        notice.setId(id);
        return noticeRepository.save(notice);
    }
    
}
