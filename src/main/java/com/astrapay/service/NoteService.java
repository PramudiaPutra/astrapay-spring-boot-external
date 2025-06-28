package com.astrapay.service;

import com.astrapay.dto.NoteDto;
import com.astrapay.entity.Note;
import com.astrapay.exception.ResourceNotFoundException;
import com.astrapay.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@Slf4j
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(NoteDto noteDto) {
        Note note = Note.builder()
                .title(noteDto.getTitle())
                .description(noteDto.getDescription())
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();

        return noteRepository.save(note);
    }

    public List<Note> getNote() {
        return noteRepository.findAll();
    }

    public Note getNote(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cannot found note with id: " + id));
    }

    public void deleteNote(Long id) {
        getNote(id);
        noteRepository.deleteById(id);
    }
}
