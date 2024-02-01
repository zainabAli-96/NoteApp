package com.noteApp.springNoteApp.Service;

import com.noteApp.springNoteApp.Entity.User;
import com.noteApp.springNoteApp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByMobileNumber(username).orElseThrow(() ->
                        new UsernameNotFoundException("Mobile number not found"));
            }
        };
    }

    @Override
    public Long getUserIdByUsername(String username) {
        Optional<User> user = userRepository.findByMobileNumber(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        User user_ = user.get();
        return user_.getId();
    }
}
