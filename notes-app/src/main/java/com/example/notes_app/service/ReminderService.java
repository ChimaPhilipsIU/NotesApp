package com.example.notes_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.notes_app.model.Reminder;
import com.example.notes_app.repository.ReminderRepository;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    // 2. Get all notes
    //    Returns a list of all reminder to display
    public List<Reminder> getAllReminders() {
        List<Reminder> reminders = new ArrayList<>();
        reminderRepository.findAll().forEach(reminders::add);
        return reminders;
    }

    // 3. Get a single reminder by its ID
    //    Used when a user clicks on a reminder to edit it
    public Reminder getReminderById(Long id) {
        return reminderRepository.findById(id).orElseThrow();
    }

    // 4. Create a new reminder
    //    Called when the user submits the create reminder form
    public void createReminder(Reminder reminder) {
        reminderRepository.save(reminder);
    }

    // 5. Update an existing reminder
    //    Called when the user saves changes on the edit screen
    public void updateReminder(Reminder reminder) {
        reminderRepository.save(reminder);
    }

    // 6. Delete a reminder
    //    Called when the user clicks the delete button on the edit screen
    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }

    public Iterable<Reminder> searchReminders(String keyword){
        return reminderRepository.findByTitleContaining(keyword);
    }
}