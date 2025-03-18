package com.noticeboard.controller;


import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noticeboard.model.Notice;
import com.noticeboard.service.NoticeService;

@WebMvcTest
public class NoticeControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoticeService noticeService;

    private Notice notice;
    
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        notice = new Notice("Title", "Description");
        notice.setId(1L);
    }

    // Test Create
    @Test
    void createNotice() throws Exception {
        when(noticeService.createNotice(any(Notice.class))).thenReturn(notice);

        mockMvc.perform(post("/notice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(notice)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.Title").value("Title"))
                .andExpect(jsonPath("$.Description").value("Description"));

        verify(noticeService, times(1)).createNotice(any(Notice.class));
    }

    // Test Read By ID
    @Test
    void getNoticeByID() throws Exception {
        when(noticeService.getNoticeById(1L)).thenReturn(Optional.of(notice));

        mockMvc.perform(get("/notice/{id}", 1L))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.Title").value("Title"))
            .andExpect(jsonPath("$.Description").value("Description"));

        verify(noticeService, times(1)).getNoticeById(1L);
    }

    // Test Delete
    @Test
    void deletedById() throws Exception{
        doNothing().when(noticeService).getNoticeById(1L);

        mockMvc.perform(delete("/notice/{id}", 1L))
            .andExpect(status().isNoContent());

        verify(noticeService, times(1)).deleteNoticeById(1L);
    }


}
