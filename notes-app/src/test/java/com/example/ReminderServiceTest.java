package com.example.notes_app.service;

import com.example.notes_app.model.Reminder;
import com.example.notes_app.repository.ReminderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReminderServiceTest {

    @Mock
    private ReminderRepository reminderRepository;

    @InjectMocks
    private ReminderService reminderService;

    @Test
    void getAllReminders_returnsOnlyFutureReminders() {
        Reminder future = new Reminder();
        future.setTitle("Future");
        future.setDueDate(LocalDateTime.now().plusDays(1));

        Reminder past = new Reminder();
        past.setTitle("Past");
        past.setDueDate(LocalDateTime.now().minusDays(1));

        when(reminderRepository.findAll()).thenReturn(List.of(future, past));

        List<Reminder> result = reminderService.getAllReminders();

        assertEquals(1, result.size());
        assertEquals("Future", result.get(0).getTitle());
    }

    @Test
    void getReminderById_returnsCorrectReminder() {
        Reminder reminder = new Reminder();
        reminder.setTitle("Test Reminder");

        when(reminderRepository.findById(1L)).thenReturn(Optional.of(reminder));

        Reminder result = reminderService.getReminderById(1L);

        assertEquals("Test Reminder", result.getTitle());
        verify(reminderRepository, times(1)).findById(1L);
    }

    @Test
    void createReminder_savesAndSetsCreatedAt() {
        Reminder reminder = new Reminder();
        reminder.setTitle("New Reminder");

        reminderService.createReminder(reminder);

        assertNotNull(reminder.getCreatedAt());
        verify(reminderRepository, times(1)).save(reminder);
    }

    @Test
    void searchReminders_returnsMatchingReminders() {
        Reminder reminder = new Reminder();
        reminder.setTitle("Doctor Appointment");

        when(reminderRepository.findByTitleContaining("Doctor")).thenReturn(List.of(reminder));

        Iterable<Reminder> result = reminderService.searchReminders("Doctor");

        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
        verify
        
        (reminderRepository, times(1)).findByTitleContaining("Doctor");
    }

    @Test
    void searchReminders_returnsEmpty_whenNoMatch() {
        when(reminderRepository.findByTitleContaining("xyz")).thenReturn(List.of());

        Iterable<Reminder> result = reminderService.searchReminders("xyz");

        assertFalse(result.iterator().hasNext());
        verify(reminderRepository, times(1)).findByTitleContaining("xyz");
    }

    @Test
    void deleteReminder_deletesById() {
        reminderService.deleteReminder(1L);

        verify(reminderRepository, times(1)).deleteById(1L);
    }

    @Test
void getAllReminders_returnsRemindersWithFolderIntact() {
    Reminder reminder1 = new Reminder();
    reminder1.setTitle("Quarterly Review");
    reminder1.setFolder("Work");
    reminder1.setDueDate(LocalDateTime.now().plusDays(1));

    Reminder reminder2 = new Reminder();
    reminder2.setTitle("Dentist");
    reminder2.setDueDate(LocalDateTime.now().plusDays(1));

    when(reminderRepository.findAll()).thenReturn(List.of(reminder1, reminder2));

    List<Reminder> result = reminderService.getAllReminders();

    assertEquals("Work", result.get(0).getFolder());
    assertNull(result.get(1).getFolder());
}

@Test
void searchReminders_byFolderName_returnsRemindersInFolder() {
    Reminder reminder = new Reminder();
    reminder.setTitle("Quarterly Review");
    reminder.setFolder("Work");

    when(reminderRepository.findByTitleContaining("Work")).thenReturn(List.of(reminder));

    Iterable<Reminder> result = reminderService.searchReminders("Work");

    assertTrue(result.iterator().hasNext());
    assertEquals("Work", result.iterator().next().getFolder());
}

@Test
void createReminder_withEmptyStringFolder_treatedAsNoFolder() {
    Reminder reminder = new Reminder();
    reminder.setTitle("Dentist");
    reminder.setFolder("");
    reminder.setDueDate(LocalDateTime.now().plusDays(1));

    reminderService.createReminder(reminder);

    assertTrue(reminder.getFolder() == null || reminder.getFolder().isEmpty());
    verify(reminderRepository, times(1)).save(reminder);
}
}