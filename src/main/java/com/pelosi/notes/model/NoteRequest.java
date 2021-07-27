package com.pelosi.notes.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequest {

    private String noteDescription;

    private String noteSender;
    private String noteRecipient;
}
