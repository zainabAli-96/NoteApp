package com.noteApp.springNoteApp.Repository;

import com.noteApp.springNoteApp.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Long> {
}
