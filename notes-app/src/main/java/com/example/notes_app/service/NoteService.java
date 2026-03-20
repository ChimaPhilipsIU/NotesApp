package com.example.notes_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.notes_app.model.Note;
import com.example.notes_app.repository.NoteRepository;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // 2. Get all notes
    //    Returns a list of all notes to display on the home screen
    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        noteRepository.findAll().forEach(notes::add);
        return notes;
    }

    // 3. Get a single note by its ID
    //    Used when a user clicks on a note to edit it
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow();
    }

    // 4. Create a new note
    //    Called when the user submits the create note form
    public void createNote(Note note) {
        noteRepository.save(note);
    }

    // 5. Update an existing note
    //    Called when the user saves changes on the edit screen
    public void updateNote(Note note) {
        noteRepository.save(note);
    }

    // 6. Delete a note
    //    Called when the user clicks the delete button on the edit screen
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
    // 7. Search for a note
    //Called when a user clicks submit in the search bar on the notes screen.

    public Iterable<Note> searchNotes(String keyword){
        return noteRepository.findByTitleContaining(keyword);
    }
}