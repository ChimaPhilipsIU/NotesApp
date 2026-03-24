package com.example.notes_app.model;


import java.time.LocalDateTime;



import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("NOTES")
public class Note {

    @Id
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private String folder;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public String getFolder() { 
        return folder; 
        }
    
    public void setFolder(String folder) {
         this.folder = folder; 
         }
}