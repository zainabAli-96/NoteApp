package com.noteApp.springNoteApp.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="folders")
public class Folder {
    @Id
    @GeneratedValue
    private Long id;
    private String folderName;
    private boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_folder_user"))
    private User user;


}
