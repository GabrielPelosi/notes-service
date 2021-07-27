package com.pelosi.notes.api.controller;

import com.pelosi.notes.exception.NoteNotFoundExecption;
import com.pelosi.notes.model.NoteRequest;
import com.pelosi.notes.model.NoteResponse;
import com.pelosi.notes.repository.entity.Note;
import com.pelosi.notes.service.NoteService;
import com.pelosi.notes.util.NoteFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DisplayName("Unit tests to NoteController")
class NoteControllerUnitTest {

    @InjectMocks
    private NoteController noteController;

    @Mock
    private NoteService noteServiceMock;


    @Test
    @DisplayName("Unit tests to getAllNotes")
    void getAllNotes_ReturnListOfNotesInsidePages_WhenSuccessful() {
        //Arrange
        PageImpl<NoteResponse> notePage = new PageImpl<>(NoteFactory.createNoteResponseList());

        BDDMockito.when(noteServiceMock.findAllNotes(ArgumentMatchers.any()))
                .thenReturn(notePage);

        //Act
        Page<NoteResponse> notes = noteController.getAllNotes(null).getBody();

        //Assert
        Assertions.assertThat(notes).isNotNull().isNotEmpty().hasSize(3);
        Assertions.assertThat(notes.toList().get(0)).isNotNull();
        Assertions.assertThat(notes.toList().get(0).getNoteDescription()).isNotNull();
        Assertions.assertThat(notes.toList().get(0).getNoteDescription()).isEqualTo("Gostaria de desejar a todos um bom dia!");
        Assertions.assertThat(notes.toList().get(1).getNoteDescription()).isNotNull();
        Assertions.assertThat(notes.toList().get(1).getNoteDescription()).isEqualTo("Gostaria de desejar a todos uma boa noite!");
        Assertions.assertThat(notes.toList().get(2).getNoteDescription()).isNotNull();
        Assertions.assertThat(notes.toList().get(2).getNoteDescription()).isEqualTo("Gostaria de desejar a todos um bom fim de semana!");
    }

    @Test
    @DisplayName("Unit tests to createNewNote")
    void createNewNote_ReturnNoteResponse_WhenSuccessful() {
        //Arrange
        NoteRequest noteToBeSavedRequest = NoteFactory.createNoteToBeSavedRequest();
        NoteResponse noteSaved = NoteFactory.createNoteSavedResponse();

        BDDMockito.when(noteServiceMock.createNewNote(ArgumentMatchers.any(NoteRequest.class)))
                .thenReturn(noteSaved);


        //Act
        NoteResponse note = noteController.createNewNote(noteToBeSavedRequest).getBody();


        //Assert
        Assertions.assertThat(note).isNotNull();
        Assertions.assertThat(note.getNoteDescription()).isNotNull().isEqualTo("Receita para fazer bolo para vocês!");
        Assertions.assertThat(note.getId()).isNotNull().isEqualTo(1L);
    }

    @Test
    @DisplayName("Unit tests to getNoteById")
    void getNoteById_ReturnNoteResponse_WhenSuccessful() {
        //Arrange
        NoteResponse noteResponse = NoteFactory.createNoteSavedResponse();
        BDDMockito.when(noteServiceMock.findNoteById(ArgumentMatchers.anyLong()))
                .thenReturn(noteResponse);

        //Act
        NoteResponse noteResponse1 = noteController.getNoteById(2L).getBody();

        //Assert
        Assertions.assertThat(noteResponse1).isNotNull().isEqualTo(noteResponse);
    }

    @Test
    @DisplayName("Unit tests to getNoteById failed")
    void getNoteById_ReturnNull_WhenNoteNotFoundException() {
        //Arrange
        BDDMockito.when(noteServiceMock.findNoteById(ArgumentMatchers.anyLong()))
                .thenThrow(new NoteNotFoundExecption("Publicação não encontrada."));

        //Act, Assert
        Assertions.assertThatThrownBy(() ->
            noteController.getNoteById(2L).getBody()
        ).isInstanceOf(NoteNotFoundExecption.class).hasMessageContaining("Publicação não encontrada.");
    }

    @Test
    @DisplayName("Unit tests to getNoteById failed")
    void getNoteByName_ReturnPAgesOfNotes_WhenSuccessful() {
        //Arrange
        PageImpl<NoteResponse> notePage = new PageImpl<>(NoteFactory.createNoteResponseList());
        BDDMockito.when(noteServiceMock.findAllNotesByName(ArgumentMatchers.any(),ArgumentMatchers.any()))
                .thenReturn(notePage);

        //Act, Assert
        Assertions.assertThat(noteController.getNotesByName("Gas",null).getBody())
                .isNotNull().isNotEmpty();
    }
}