package com.pelosi.notes.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Table(name = "tb_notes")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;


    @NotBlank(message = "Descrição não pode estar vazia(Blank)")
    @NotNull(message = "Descrição não pode estar vazia(Null)")
    @NotEmpty(message = "Descrição não pode estar vazia(Empty)")
    @Column(nullable = false,updatable = false)
    private String noteDescription;

    @NotBlank(message = "Remetente não pode estar vazio(Blank)")
    @NotNull(message = "Remetente não pode estar vazio(Null)")
    @NotEmpty(message = "Remetente não pode estar vazio(Empty)")
    @Column(nullable = false,updatable = false)
    private String noteSender;

    @NotBlank(message = "Remetente não pode estar vazio(Blank)")
    @NotNull(message = "Remetente não pode estar vazio(Null)")
    @NotEmpty(message = "Remetente não pode estar vazio(Empty)")
    @Column(nullable = false,updatable = false)
    private String noteRecipient;

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdDateTime;
}
