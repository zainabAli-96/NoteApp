package com.noteApp.springNoteApp.Entity;

import com.noteApp.springNoteApp.DTO.FolderResponse;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="notes")
public class Note {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder;



}
