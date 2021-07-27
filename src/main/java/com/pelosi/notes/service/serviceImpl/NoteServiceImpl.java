package com.pelosi.notes.service.serviceImpl;

import com.pelosi.notes.exception.NoteNotFoundExecption;
import com.pelosi.notes.model.NoteRequest;
import com.pelosi.notes.model.NoteResponse;
import com.pelosi.notes.repository.NoteRepository;
import com.pelosi.notes.repository.entity.Note;
import com.pelosi.notes.service.NoteService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final @NonNull NoteRepository noteRepository;


    @Transactional(readOnly = true)
    @Override
    public NoteResponse findNoteById(Long id) {
        return noteRepository.findById(id).map(note -> NoteResponse
                .builder()
                .noteDescription(note.getNoteDescription())
                .noteSender(note.getNoteSender())
                .noteDescription(note.getNoteDescription())
                .id(note.getId())
                .createdDate(note.getCreatedDate())
                .build()).orElseThrow(() -> new NoteNotFoundExecption("Nota nao encontrada."));
    }

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
                .createdDate(note.getCreatedDate())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<NoteResponse> findAllNotes(Pageable pageable) {
        return noteRepository.findAll(pageable).map(note -> NoteResponse
                .builder()
                .noteSender(note.getNoteSender())
                .noteRecipient(note.getNoteRecipient())
                .noteDescription(note.getNoteDescription())
                .id(note.getId())
                .createdDate(note.getCreatedDate())
                .build());
    }

    @Override
    public Page<NoteResponse> findAllNotesByName(String name, Pageable pageable) {
        return noteRepository
                .findAllByNoteSenderContainingOrderByCreatedDate(name, pageable)
                .map(note -> NoteResponse
                        .builder()
                        .noteSender(note.getNoteSender())
                        .noteRecipient(note.getNoteRecipient())
                        .noteDescription(note.getNoteDescription())
                        .id(note.getId())
                        .createdDate(note.getCreatedDate())
                        .build()
                );
    }

    @Override
    public Optional<Boolean> deleteNoteById(Long id) {
        return noteRepository.findById(id).map(note -> {
                noteRepository.delete(note);
                return true;
            }
        );
    }
}
