package com.noteApp.springNoteApp.DTO;

import lombok.Data;

@Data
public class FolderResponse {


    private Long id;
    private String folderName;
    private boolean isPublic;


    public FolderResponse(Long id, String folderName, boolean isPublic) {

this.id = id;
        this.folderName= folderName;
        this.isPublic = isPublic;

    }

    public FolderResponse() {

    }


    public void setMessage(String access_denied) {

    }
}




