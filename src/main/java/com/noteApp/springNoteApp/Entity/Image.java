package com.noteApp.springNoteApp.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue
    private Long id;


    private String fileName;

    private String filePath;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder;

}