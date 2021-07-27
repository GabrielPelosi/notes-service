package com.pelosi.notes.api.controller;

import com.pelosi.notes.model.NoteRequest;
import com.pelosi.notes.model.NoteResponse;
import com.pelosi.notes.repository.NoteRepository;
import com.pelosi.notes.repository.entity.Note;
import com.pelosi.notes.util.NoteFactory;
import com.pelosi.notes.util.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
class NoteControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private NoteRepository noteRepository;


    @Test
    void getAllNotes_ReturnPageableResponseWithNotes_whenSuccessful() {
        Note savedNote = noteRepository.save(NoteFactory.createNoteToBeSaved());

        String expectedDesc = savedNote.getNoteDescription();

        PageableResponse<Note> notePage = testRestTemplate.exchange("/notes", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Note>>() {
                }).getBody();

        Assertions.assertThat(notePage).isNotNull();
        Assertions.assertThat(notePage.toList()).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(notePage.toList().get(0).getNoteDescription()).isNotNull().isNotEmpty()
        .isEqualTo(expectedDesc);

    }

    @Test
    void getAllNotesByName_ReturnPageableResponseWithNotes_whenSuccessful() {
        Note savedNote = noteRepository.save(NoteFactory.createNoteToBeSaved());
        System.out.println(noteRepository.findAll().toString());
        String expectedDesc = savedNote.getNoteDescription();

        PageableResponse<NoteResponse> notePage = testRestTemplate.exchange("/notes/find?name='hgh'", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<NoteResponse>>() {
                }).getBody();

        Assertions.assertThat(notePage).isNotNull().isEmpty();

    }

    @Test
    void createNewNote_ReturnCreatedNote_WhenSuccessful() {
        NoteRequest noteToBeSavedRequest = NoteFactory.createNoteToBeSavedRequest();

        ResponseEntity<NoteResponse> noteResponseResponseEntity = testRestTemplate
                .postForEntity("/notes",noteToBeSavedRequest,NoteResponse.class);

        Assertions.assertThat(noteResponseResponseEntity).isNotNull();
        Assertions.assertThat(noteResponseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(noteResponseResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(noteResponseResponseEntity.getBody().getNoteDescription()).isNotNull().isNotEmpty();
        Assertions.assertThat(noteResponseResponseEntity.getBody().getId()).isNotNull();

    }

    @Test
    void getNoteById_ReturnNoteResponse_WhenSuccessful() {
        Note savedNote = noteRepository.save(NoteFactory.createNoteToBeSaved());

        String expectedDesc = savedNote.getNoteDescription();

        NoteResponse noteResponse = testRestTemplate.getForObject("/notes/{id}",NoteResponse.class,savedNote.getId());

        Assertions.assertThat(noteResponse).isNotNull();
        Assertions.assertThat(noteResponse.getId()).isNotNull().isEqualTo(savedNote.getId());
        Assertions.assertThat(noteResponse.getNoteDescription()).isNotNull().isEqualTo(savedNote.getNoteDescription());

    }

    @Test
    void removeNote_ReturnFalseNotAuthUser_WheFailed() {

    }

    @Test
    void removeNote_ReturnTrueAuthUser_WheSuccess() {

    }
}