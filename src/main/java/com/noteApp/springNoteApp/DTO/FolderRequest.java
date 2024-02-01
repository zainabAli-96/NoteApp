package com.noteApp.springNoteApp.DTO;

import lombok.Data;

@Data
public class FolderRequest {

    private String folderName;
    private boolean isPublic;
}
