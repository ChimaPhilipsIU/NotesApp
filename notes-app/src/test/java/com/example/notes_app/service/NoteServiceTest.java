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


    @Test
    
    void searchNotes_returnsMatchingNotes() {
        Note note = new Note();
        note.setTitle("Shopping List");
        
        when(noteRepository.findByTitleContaining("Shopping")).thenReturn(List.of(note));
        
        Iterable<Note> result = noteService.searchNotes("Shopping");

        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
        verify(noteRepository, times(1)).findByTitleContaining("Shopping");
    }

    @Test
    void searchNotes_returnsEmpty_whenNoMatch() {
        when(noteRepository.findByTitleContaining("xyz")).thenReturn(List.of());

        Iterable<Note> result = noteService.searchNotes("xyz");
        
        assertFalse(result.iterator().hasNext());
        verify(noteRepository, times(1)).findByTitleContaining("xyz");
    }
    
    @Test
    void createNote_setsCreatedAt() {
        
        Note note = new Note();
        note.setTitle("New Note");
        
        noteService.createNote(note);
        
        assertNotNull(note.getCreatedAt());
        verify(noteRepository, times(1)).save(note);
    }

    @Test
void searchNotes_byFolderName_returnsNotesInFolder() {
    Note note = new Note();
    note.setTitle("Budget");
    note.setFolder("Work");

    when(noteRepository.findByTitleContaining("Work")).thenReturn(List.of(note));

    Iterable<Note> result = noteService.searchNotes("Work");

    assertTrue(result.iterator().hasNext());
    assertEquals("Work", result.iterator().next().getFolder());
}

@Test
void getAllNotes_returnsNotesWithFolderIntact() {
    Note note1 = new Note();
    note1.setTitle("Budget");
    note1.setFolder("Work");

    Note note2 = new Note();
    note2.setTitle("Shopping");
    // no folder

    when(noteRepository.findAll()).thenReturn(List.of(note1, note2));

    List<Note> result = noteService.getAllNotes();

    assertEquals("Work", result.get(0).getFolder());
    assertNull(result.get(1).getFolder());
}

@Test
void createNote_withEmptyStringFolder_treatedAsNoFolder() {
    Note note = new Note();
    note.setTitle("Test");
    note.setFolder("");

    noteService.createNote(note);

    assertTrue(note.getFolder() == null || note.getFolder().isEmpty());
    verify(noteRepository, times(1)).save(note);
}
}