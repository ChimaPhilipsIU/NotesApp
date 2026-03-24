package com.example.notes_app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.notes_app.model.Note;
import com.example.notes_app.service.NoteService;
import com.example.notes_app.service.ReminderService;


@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private ReminderService reminderService;


    // Show all notes on the home screen
    @GetMapping("/notes")
    public String getAllNotes(Model model) {
        model.addAttribute("notes", noteService.getAllNotes());
        model.addAttribute("reminders", reminderService.getAllReminders());
        return "notes";
    }

    // Show the create note form
    @GetMapping("/notes/create")
    public String showCreateForm(Model model) {
        model.addAttribute("note", new Note());
        return "create-note";
    }

    // Handle the create note form submission
    @PostMapping("/notes/create")
    public String createNote(Note note) {
        noteService.createNote(note);
        return "redirect:/notes";
    }

    // Show the edit note form
    @GetMapping("/notes/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("note", noteService.getNoteById(id));
        return "edit-note";
    }

    // Handle the edit note form submission
    @PostMapping("/notes/{id}/edit")
    public String updateNote(Note note) {
        noteService.updateNote(note);
        return "redirect:/notes";
    }

    // Handle note deletion
    @PostMapping("/notes/{id}/delete")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/notes";
    }

    @GetMapping("/notes/search")
    @ResponseBody
    public Iterable<Note> searchNotes(@RequestParam String keyword){
        return noteService.searchNotes(keyword);
}

    @GetMapping("/")
    public String index() {
        return "redirect:/notes";
}

@PostMapping("/notes/{id}/folder")
public ResponseEntity<Void> updateNoteFolder(@PathVariable Long id, @RequestParam String folder) {
    Note note = noteService.getNoteById(id);
    note.setFolder(folder);
    noteService.updateNote(note);
    return ResponseEntity.ok().build();
}



@GetMapping("/notes/{id}/json")
@ResponseBody
public Note getNoteJson(@PathVariable Long id) {
    return noteService.getNoteById(id);
}
}


