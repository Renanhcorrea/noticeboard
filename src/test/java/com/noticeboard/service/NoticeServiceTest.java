package com.noticeboard.service;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.noticeboard.model.Notice;
import com.noticeboard.repository.NoticeRepository;


public class NoticeServiceTest {

    @Mock
    private NoticeRepository noticeRepository;

    @InjectMocks
    private NoticeService noticeService;

    private Notice notice;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        notice = new Notice("testOne", "testTwo");
        notice.setId(1L);
        notice.setPublicationDate(LocalDateTime.now());
    }

    // Test Create
    @Test
    void createNotice(){
        when(noticeRepository.save(any(Notice.class))).thenReturn(notice);
        
        Notice createNotice = noticeService.createNotice(notice);

        assertNotNull(createNotice);
        assertEquals("testOne", createNotice.getTitle());
        assertEquals("testTwo", createNotice.getDescription());
        verify(noticeRepository, times(1)).save(any(Notice.class));
    }

    // Test Read by ID
    @Test
    void getNoticeByID(){
        when(noticeRepository.findById(1L)).thenReturn(Optional.of(notice));

        Optional<Notice> foundNotice = noticeService.getNoticeById(1L);

        foundNotice.ifPresent(notices ->{
            assertEquals(1L, notices.getId());
            assertEquals("testOne", notices.getTitle());
        });
        verify(noticeRepository, times(1)).findById(1L);
    }

    // Test Update
    @Test
    void updateNotice(){
        Notice updatedNotice = new Notice("Updated Title", "Updated Description");
        updatedNotice.setId(1L);

        when(noticeRepository.existsById(1L)).thenReturn(true);
        when(noticeRepository.save(any(Notice.class))).thenReturn(updatedNotice);

        Notice result = noticeService.updatedNotice(1L, updatedNotice);

        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Description", result.getDescription());
        verify(noticeRepository, times(1)).existsById(1L);
        verify(noticeRepository, times(1)).save(any(Notice.class));
    }

    // Test Delete
    @Test
    void deleteNotice(){
        when(noticeRepository.existsById(1L)).thenReturn(true);

        noticeService.deleteNoticeById(1L);

        verify(noticeRepository, times(1)).deleteById(1L);
    }

}
