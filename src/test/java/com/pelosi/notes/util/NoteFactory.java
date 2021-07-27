package com.pelosi.notes.util;

import com.pelosi.notes.model.NoteRequest;
import com.pelosi.notes.model.NoteResponse;
import com.pelosi.notes.repository.entity.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteFactory {


    public static Note createNoteToBeSaved(){
        return Note.builder().id(null).noteSender("Gabriel")
                .noteRecipient("Gabriel")
                .noteDescription("Bom dia!").build();
    }

    public static NoteRequest createNoteToBeSavedRequest(){
        return NoteRequest.builder()
                .noteSender("Gabriel")
                .noteRecipient("Gabriel")
                .noteDescription("Bom dia!").build();
    }

    public static NoteResponse createNoteSavedResponse(){
        return NoteResponse.builder().id(2L)
                .noteSender("Gabriel")
                .noteRecipient("Gabriel")
                .noteDescription("Bom dia!").build();
    }


    public static List<NoteResponse> createNoteResponseList(){
        List<NoteResponse> noteResponses = new ArrayList<>();
        noteResponses.add(NoteResponse.builder().id(1L)
                .noteSender("Gasbfd").noteRecipient("Gabriel")
                .noteDescription("Gostaria de desejar a todos um bom dia!").build());
        noteResponses.add(NoteResponse.builder().id(2L).noteSender("Gasbfd").noteRecipient("Gabriel").noteDescription("Gostaria de desejar a todos uma boa noite!").build());
        noteResponses.add(NoteResponse.builder().id(3L).noteSender("Gasbfd").noteRecipient("Gabriel").noteDescription("Gostaria de desejar a todos um bom fim de semana!").build());
        return noteResponses;
    }
}
