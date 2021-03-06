package com.pelosi.notes.service.serviceImpl;

import com.pelosi.notes.exception.NoteNotFoundExecption;
import com.pelosi.notes.model.NoteRequest;
import com.pelosi.notes.model.NoteResponse;
import com.pelosi.notes.repository.NoteRepository;
import com.pelosi.notes.repository.entity.Note;
import com.pelosi.notes.service.NoteService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final @NonNull NoteRepository noteRepository;


    @Transactional(readOnly = true)
    @Cacheable(value = "note-cache", key = "#id")
    @Override
    public NoteResponse findNoteById(Long id) {
        return noteRepository.findById(id).map(note -> NoteResponse
                .builder()
                .noteDescription(note.getNoteDescription())
                .noteSender(note.getNoteSender())
                .noteDescription(note.getNoteDescription())
                .id(note.getId())
                .createdDate(note.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build()).orElseThrow(() -> new NoteNotFoundExecption("Nota nao encontrada."));
    }

    @CachePut(value = "note-cache")
    @Override
    public NoteResponse createNewNote(NoteRequest noteRequest) {
        Note note = Note.builder()
                .noteSender(noteRequest.getNoteSender())
                .noteRecipient(noteRequest.getNoteRecipient())
                .noteDescription(noteRequest.getNoteDescription())
                .build();
        Note savedNote = noteRepository.save(note);
        return NoteResponse.builder()
                .id(savedNote.getId())
                .noteDescription(savedNote.getNoteDescription())
                .noteSender(note.getNoteSender())
                .noteRecipient(note.getNoteRecipient())
                .createdDate(note.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "note-cache", key = "#pageable.pageNumber")
    @CacheEvict(value = "note-cache", key = "#pageable.pageNumber",beforeInvocation = true)
    @Override
    public Page<NoteResponse> findAllNotes(Pageable pageable) {
        return noteRepository.findAll(pageable).map(note -> NoteResponse
                .builder()
                .noteSender(note.getNoteSender())
                .noteRecipient(note.getNoteRecipient())
                .noteDescription(note.getNoteDescription())
                .id(note.getId())
                .createdDate(note.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build());
    }

    @Override
    public Page<NoteResponse> findAllNotesByName(String name, Pageable pageable) {
        return noteRepository
                .findAllByNoteSenderContainingOrderByCreatedDateTime(name, pageable)
                .map(note -> NoteResponse
                        .builder()
                        .noteSender(note.getNoteSender())
                        .noteRecipient(note.getNoteRecipient())
                        .noteDescription(note.getNoteDescription())
                        .id(note.getId())
                        .createdDate(note.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                        .build()
                );
    }

    @CacheEvict(value = "note-cache",key = "#id",beforeInvocation = true)
    @Override
    public Optional<Boolean> deleteNoteById(Long id) {
        return noteRepository.findById(id).map(note -> {
                noteRepository.delete(note);
                return true;
            }
        );
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "note-cache")
    @CacheEvict(value = "note-cache", allEntries = true,beforeInvocation = true)
    public List<NoteResponse> getAllNotes() {
        return noteRepository.findAll().stream().map(note ->
                NoteResponse.builder().noteSender(note.getNoteSender())
                .noteRecipient(note.getNoteRecipient())
                .noteDescription(note.getNoteDescription())
                .createdDate(note.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .id(note.getId()).build())
                .collect(Collectors.toList());
    }
}
