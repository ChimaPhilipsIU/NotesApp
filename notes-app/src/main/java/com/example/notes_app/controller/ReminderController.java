package com.example.notes_app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.notes_app.model.Reminder;
import com.example.notes_app.service.ReminderService;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

 

    // Show the create remidner form
    @GetMapping("/reminders/create")
    public String showCreateForm(Model model) {
        model.addAttribute("reminder", new Reminder());
        return "create-reminder";
    }

    // Handle the create note form submission
    @PostMapping("/reminders/create")
    public String createReminder(Reminder reminder) {
        reminderService.createReminder(reminder);
        return "redirect:/notes";
    }

    // Show the edit note form
    @GetMapping("/reminders/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("reminder", reminderService.getReminderById(id));
        return "edit-reminder";
    }

    // Handle the edit note form submission
    @PostMapping("/reminders/{id}/edit")
    public String updateReminder(Reminder reminder) {
        reminderService.updateReminder(reminder);
        return "redirect:/notes";
    }

    // Handle note deletion
    @PostMapping("/reminders/{id}/delete")
    public String deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
        return "redirect:/notes";
    }

    @GetMapping("/reminders/search")
    @ResponseBody
    public Iterable<Reminder> searchReminders(@RequestParam String keyword){
        return reminderService.searchReminders(keyword);
}
}


