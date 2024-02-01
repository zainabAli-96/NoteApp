package com.noteApp.springNoteApp.DTO;

import lombok.Data;

@Data
public class CreateNoteRequest {
    private String title;
    private String content;
}
