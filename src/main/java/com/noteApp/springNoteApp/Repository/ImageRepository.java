package com.noteApp.springNoteApp.Repository;

import com.noteApp.springNoteApp.Entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
