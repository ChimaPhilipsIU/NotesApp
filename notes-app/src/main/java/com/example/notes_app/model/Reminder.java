package com.example.notes_app.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("REMINDERS")
public class Reminder {

    @Id
    private Long id;

    private String title;
    
    private String content;

    private LocalDateTime dueDate;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDueDate() {
    return dueDate;
}

public void setDueDate(LocalDateTime dueDate) {
    this.dueDate = dueDate;
}


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}