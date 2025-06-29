package com.astrapay.service;

import com.astrapay.dto.NoteDto;
import com.astrapay.entity.Note;
import com.astrapay.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateNote() {
        NoteDto noteDto = NoteDto.builder()
                .title("Test Title")
                .description("Test Description")
                .build();

        Note note = Note.builder()
                .id(1L)
                .title("Test Title")
                .description("Test Description")
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();

        when(noteRepository.save(any(Note.class))).thenReturn(note);

        Note createdNote = noteService.createNote(noteDto);

        assertThat(createdNote.getTitle()).isEqualTo(noteDto.getTitle());
    }

    @Test
    public void testGetAllNotes() {
        Note note1 = Note.builder()
                .id(1L)
                .title("Test Title1")
                .build();

        Note note2 = Note.builder()
                .id(2L)
                .title("Test Title2")
                .build();

        when(noteRepository.findAll()).thenReturn(Arrays.asList(note1, note2));

        List<Note> notes = noteService.getNote();

        assertThat(notes).hasSize(2);
        assertThat(notes.get(0).getTitle()).isEqualTo("Test Title1");
    }

    @Test
    public void testGetNote() {
        Note note = Note.builder()
                .id(1L)
                .title("Test Title")
                .build();

        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));

        Note foundNote = noteService.getNote(1L);

        assertThat(foundNote.getTitle()).isEqualTo("Test Title");
    }

    @Test
    public void testDeleteNote() {
        Note note = Note.builder()
                .id(1L)
                .title("Test Title")
                .build();

        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));
        doNothing().when(noteRepository).deleteById(1L);
        noteService.deleteNote(1L);
        verify(noteRepository, times(1)).deleteById(1L);
    }
}
