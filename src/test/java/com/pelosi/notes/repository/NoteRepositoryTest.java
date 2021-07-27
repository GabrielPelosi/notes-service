package com.pelosi.notes.repository;

import com.pelosi.notes.repository.entity.Note;
import com.pelosi.notes.util.NoteFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;


@DataJpaTest
@DisplayName("Tests for Note Repository")
class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;


     @Test
     @DisplayName("Save note when successful")
     public void save_PersistNote_WhenSuccessful(){
         //Arrange
        Note noteToBeSaved = NoteFactory.createNoteToBeSaved();
        //Act
        Note savedNote = noteRepository.save(noteToBeSaved);
        //Assert
         Assertions.assertThat(savedNote).isNotNull();
         Assertions.assertThat(savedNote.getId()).isNotNull();
         Assertions.assertThat(savedNote.getNoteDescription()).isNotNull();
         Assertions.assertThat(savedNote.getNoteDescription()).isNotBlank();
         Assertions.assertThat(savedNote.getNoteDescription()).isNotEmpty();
         Assertions.assertThat(savedNote.getNoteDescription()).isEqualTo(noteToBeSaved.getNoteDescription());
     }


    @Test
    @DisplayName("Save note when successful")
    public void save_ReturnNote_WhenSuccessful(){
        //Arrange
        Note noteToBeSaved = NoteFactory.createNoteToBeSaved();
        Note savedNote = noteRepository.save(noteToBeSaved);
        //Act
        Note noteFound = noteRepository.findById(savedNote.getId()).get();
        System.out.println(savedNote.toString());
        //Assert
        Assertions.assertThat(savedNote).isEqualTo(noteFound);
    }


    @Test
    @DisplayName("Save note when successful")
    public void save_ReturnListOfNotes_WhenSuccessful(){
        //Arrange
        Note noteToBeSaved = NoteFactory.createNoteToBeSaved();
        Note noteToBeSaved2 = NoteFactory.createNoteToBeSaved();
        Note noteToBeSaved3 = NoteFactory.createNoteToBeSaved();
        noteRepository.save(noteToBeSaved);
        noteRepository.save(noteToBeSaved2);
        noteRepository.save(noteToBeSaved3);

        //Act
        List<Note> notes = noteRepository.findAll();


        //Assert
        Assertions.assertThat(notes).isNotNull();
        Assertions.assertThat(notes).isNotEmpty();
        Assertions.assertThat(notes.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Find all notes by name")
    public void save_ReturnListOfNotesByName_WhenSuccessful(){
        //Arrange
        Note noteToBeSaved = NoteFactory.createNoteToBeSaved();
        Note noteToBeSaved2 = NoteFactory.createNoteToBeSaved();
        Note noteToBeSaved3 = NoteFactory.createNoteToBeSaved();
        noteRepository.save(noteToBeSaved);
        noteRepository.save(noteToBeSaved2);
        noteRepository.save(noteToBeSaved3);

        //Act
        Pageable pageable = PageRequest.of(0, 8);
        Page<Note> notes = noteRepository.findAllByNoteSenderContainingOrderByCreatedDate("Gaab",pageable);


        //Assert
        Assertions.assertThat(notes).isNotNull();
        Assertions.assertThat(notes).isNotEmpty();
    }
}