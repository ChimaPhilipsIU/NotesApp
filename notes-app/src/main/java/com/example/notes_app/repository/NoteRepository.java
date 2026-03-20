package com.example.notes_app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.notes_app.model.Note;


public interface NoteRepository extends CrudRepository<Note, Long> {
    // CrudRepository gives these methods for free:
    // - save(note)        → creates or updates a note
    // - findById(id)      → gets one note by id
    // - findAll()         → gets all notes
    // - deleteById(id)    → deletes a note by id
    //Vocab for understanding JPA querying
    // - find = SELECT
    // - By = WHERE
    // - Title = the field name in a given class
    // - Containing = Like

    Iterable<Note> findByTitleContaining(String keyword);
    //SELECT * FROM notes WHERE title LIKE '%keyword%'

}