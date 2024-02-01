package com.noteApp.springNoteApp.Service;


import com.noteApp.springNoteApp.DTO.CreateNoteRequest;
import com.noteApp.springNoteApp.DTO.FolderRequest;
import com.noteApp.springNoteApp.DTO.FolderResponse;
import com.noteApp.springNoteApp.Entity.Folder;
import com.noteApp.springNoteApp.Entity.Note;

import java.util.List;

public interface FolderService {
        FolderResponse getFolderById(Long folderId);

        List<Folder> getFoldersByUserId(Long userId);

        FolderResponse createFolder(Long userId, FolderRequest folderRequest);



}

