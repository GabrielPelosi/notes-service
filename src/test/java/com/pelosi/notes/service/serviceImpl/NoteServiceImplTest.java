package com.pelosi.notes.service.serviceImpl;

import com.pelosi.notes.repository.NoteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Unit tests to NoteService")
class NoteServiceImplTest {

    @InjectMocks
    private NoteServiceImpl noteService;

    @Mock
    private NoteRepository noteRepository;


    @Test
    void findNoteById() {
    }

    @Test
    void findNoteById_notFound() {
    }

    @Test
    void createNewNote() {
    }

    @Test
    void findAllNotes() {
    }

    @Test
    void findAllNotesByName() {
    }

    @Test
    void deleteNoteById() {
    }
}