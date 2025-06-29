package com.astrapay.controller;

import com.astrapay.dto.NoteDto;
import com.astrapay.entity.Note;
import com.astrapay.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteController.class)
public class NoteControllerTest {
    private final ObjectMapper objectMapper;
    private final MockMvc mockMvc;

    @Autowired
    NoteControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @MockBean
    private NoteService noteService;

    @Test
    public void testCreateNote() throws Exception {
        NoteDto noteDto = NoteDto.builder()
                .title("Test Note")
                .description("Test Description")
                .build();

        Note note = Note.builder()
                .id(1L)
                .title("Test Note")
                .description("Test Description")
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();

        when(noteService.createNote(any(NoteDto.class))).thenReturn(note);

        mockMvc.perform(post("/note")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noteDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Note"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    public void testGetNoteById() throws Exception {
        Note note = Note.builder()
                .id(1L)
                .title("Test Note")
                .description("Test Description")
                .build();

        when(noteService.getNote(1L)).thenReturn(note);

        mockMvc.perform(get("/note/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType((MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Note"));

    }

    @Test
    public void testGetAllNotes() throws Exception {
        Note note1 = Note.builder()
                .id(1L)
                .title("Test Title1")
                .build();

        Note note2 = Note.builder()
                .id(2L)
                .title("Test Title2")
                .build();

        List<Note> notes = Arrays.asList(note1, note2);

        when(noteService.getNote()).thenReturn(notes);

        mockMvc.perform(get("/note"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("Test Title1"))
                .andExpect(jsonPath("$[1].title").value("Test Title2"));
    }

    @Test
    public void testDeleteNote() throws Exception {
        doNothing().when(noteService).deleteNote(1L);

        mockMvc.perform(delete("/note/1")).andExpect(status().isOk());
    }
}
