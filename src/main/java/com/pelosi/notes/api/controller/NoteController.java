package com.pelosi.notes.api.controller;


import com.pelosi.notes.model.NoteRequest;
import com.pelosi.notes.model.NoteResponse;
import com.pelosi.notes.service.NoteService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/notes")
public class NoteController {

    private final @NonNull NoteService noteService;

    @GetMapping
    public ResponseEntity<Page<NoteResponse>> getAllNotes(Pageable pageable){
         return ResponseEntity.ok(noteService.findAllNotes(pageable));
    }

    @PostMapping
    public ResponseEntity<NoteResponse> createNewNote(@RequestBody NoteRequest noteRequest){
        return ResponseEntity.ok(noteService.createNewNote(noteRequest));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable Long id){
        return ResponseEntity.ok(noteService.findNoteById(id));
    }

    @GetMapping(value = "/find")
    public ResponseEntity<Page<NoteResponse>> getNotesByName(@RequestParam(value = "name") String name,Pageable pageable){
        return ResponseEntity.ok(noteService.findAllNotesByName(name,pageable));
    }
}
