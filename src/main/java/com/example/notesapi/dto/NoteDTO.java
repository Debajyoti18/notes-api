package com.example.notesapi.dto;

import jakarta.validation.constraints.NotBlank;

public class NoteDTO {

    private Integer id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Content cannot be empty")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
