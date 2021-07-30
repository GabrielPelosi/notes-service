package com.pelosi.notes.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponse {

    private Long id;

    private String noteDescription;

    private String noteSender;
    private String noteRecipient;

    private String createdDate;

}
