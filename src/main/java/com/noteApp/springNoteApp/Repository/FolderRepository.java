package com.noteApp.springNoteApp.Repository;

import com.noteApp.springNoteApp.DTO.FolderResponse;
import com.noteApp.springNoteApp.Entity.Folder;
import com.noteApp.springNoteApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folder,Long> {
    List<Folder> findByUserId(Long userId);
    Optional<User> findUserById(Long userId);


}
