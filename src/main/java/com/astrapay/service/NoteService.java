package com.astrapay.service;

import com.astrapay.dto.NoteDto;
import com.astrapay.entity.Note;
import com.astrapay.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

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
}
