package com.noteApp.springNoteApp.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
    Long getUserIdByUsername(String username);


}
