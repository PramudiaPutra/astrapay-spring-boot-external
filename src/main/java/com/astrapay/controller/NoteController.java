package com.astrapay.controller;

import com.astrapay.dto.NoteDto;
import com.astrapay.entity.Note;
import com.astrapay.service.NoteService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "NoteController")
@Slf4j
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@Valid @RequestBody NoteDto noteDto) {
        Note noteData = noteService.createNote(noteDto);
        NoteDto response = NoteDto.builder()
                .title(noteData.getTitle())
                .description(noteData.getDescription())
                .createdAt(noteData.getCreatedAt())
                .updatedAt(noteData.getUpdatedAt())
                .build();

        return ResponseEntity.ok(response);
    }
}
