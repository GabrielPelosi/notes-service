package com.pelosi.notes.repository;

import com.pelosi.notes.repository.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

    Page<Note> findAllByNoteSenderContainingOrderByCreatedDateTime(String name, Pageable pageable);

}
