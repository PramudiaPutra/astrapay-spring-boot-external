package com.astrapay.repository;

import com.astrapay.entity.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class NoteRepositoryTest {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteRepositoryTest(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Test
    public void testSaveAndFindAll() {
        Note note1 = Note.builder()
                .title("Test Title1")
                .description("Test Description1")
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();
        noteRepository.save(note1);

        Note note2 = Note.builder()
                .title("Test Title2")
                .description("Test Description2")
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();
        noteRepository.save(note2);

        List<Note> notes = noteRepository.findAll();
        assertThat(notes).hasSize(2);
    }

    @Test
    public void testSaveAndFindById() {
        Note note = Note.builder()
                .title("Test Title")
                .description("Test Description")
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();

        Note savedNote = noteRepository.save(note);
        Optional<Note> foundNote = noteRepository.findById(savedNote.getId());

        assertThat(foundNote).isPresent();
        assertThat(foundNote.get().getTitle()).isEqualTo("Test Title");

    }

    @Test
    public void testSaveAndDeleteById() {
        Note note = Note.builder()
                .title("Test Title")
                .description("Test Description")
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();

        Note savedNote = noteRepository.save(note);
        noteRepository.deleteById(savedNote.getId());
        Optional<Note> foundNote = noteRepository.findById(savedNote.getId());

        assertThat(foundNote).isNotPresent();
    }
}
