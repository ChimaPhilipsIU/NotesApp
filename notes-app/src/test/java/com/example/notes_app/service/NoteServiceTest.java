package com.example.notes_app.service;

import com.example.notes_app.model.Note;
import com.example.notes_app.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    void getAllNotes_returnsAllNotes() {
        Note note1 = new Note();
        note1.setTitle("First Note");

        Note note2 = new Note();
        note2.setTitle("Second Note");

        when(noteRepository.findAll()).thenReturn(List.of(note1, note2));

        List<Note> result = noteService.getAllNotes();

        assertEquals(2, result.size());
        verify(noteRepository, times(1)).findAll();
    }

    @Test
    void getNoteById_returnsCorrectNote() {
        Note note = new Note();
        note.setTitle("Test Note");

        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));

        Note result = noteService.getNoteById(1L);

        assertEquals("Test Note", result.getTitle());
        verify(noteRepository, times(1)).findById(1L);
    }

    @Test
    void createNote_savesNote() {
        Note note = new Note();
        note.setTitle("New Note");

        noteService.createNote(note);

        verify(noteRepository, times(1)).save(note);
    }

    @Test
    void updateNote_savesNote() {
        Note note = new Note();
        note.setTitle("Updated Note");

        noteService.updateNote(note);

        verify(noteRepository, times(1)).save(note);
    }

    @Test
    void deleteNote_deletesById() {
        noteService.deleteNote(1L);

        verify(noteRepository, times(1)).deleteById(1L);
    }
}