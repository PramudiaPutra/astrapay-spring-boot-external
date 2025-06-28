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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                .id(noteData.getId())
                .title(noteData.getTitle())
                .description(noteData.getDescription())
                .createdAt(noteData.getCreatedAt())
                .updatedAt(noteData.getUpdatedAt())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> getNote() {
        List<NoteDto> response = noteService.getNote().stream().map(noteData ->
            NoteDto.builder()
                    .id(noteData.getId())
                    .title(noteData.getTitle())
                    .description(noteData.getDescription())
                    .createdAt(noteData.getCreatedAt())
                    .updatedAt(noteData.getUpdatedAt())
                    .build()
        ).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<NoteDto> getNote(@PathVariable Long id) {
        Note noteData = noteService.getNote(id);

        NoteDto response = NoteDto.builder()
                .id(noteData.getId())
                .title(noteData.getTitle())
                .description(noteData.getDescription())
                .createdAt(noteData.getCreatedAt())
                .updatedAt(noteData.getUpdatedAt())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Object>> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Note deleted successfully",
                "id", id
        ));
    }
}
