package com.noteApp.springNoteApp.Repository;

import com.noteApp.springNoteApp.Entity.Role;
import com.noteApp.springNoteApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByMobileNumber(String mobileNumber);
    Optional<User> findById(Long id);
    User findByRole(Role role);


}
