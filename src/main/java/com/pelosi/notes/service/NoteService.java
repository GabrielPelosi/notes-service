package com.pelosi.notes.service;

import com.pelosi.notes.model.NoteRequest;
import com.pelosi.notes.model.NoteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface NoteService {

    NoteResponse findNoteById(Long id);

    NoteResponse createNewNote(NoteRequest noteRequest);

    Page<NoteResponse> findAllNotes(Pageable pageable);

    Page<NoteResponse> findAllNotesByName(String name, Pageable pageable);

    Optional<Boolean> deleteNoteById(Long id);

    List<NoteResponse> getAllNotes();


}
