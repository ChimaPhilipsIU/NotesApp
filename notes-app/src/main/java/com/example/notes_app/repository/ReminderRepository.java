package com.example.notes_app.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.notes_app.model.Reminder;

import java.util.List;



public interface ReminderRepository extends CrudRepository<Reminder, Long> {
    // CrudRepository gives these methods for free:
    // - save(reminder)        → creates or updates a reminder
    // - findById(id)      → gets one note by id
    // - findAll()         → gets all notes
    // - deleteById(id)    → deletes a note by id
    //Vocab for understanding JPA querying
    // - find = SELECT
    // - By = WHERE
    // - Title = the field name in a given class
    // - Containing = Like

    Iterable<Reminder> findByTitleContaining(String keyword);
    //SELECT * FROM reminders WHERE title LIKE 'keyword'

}