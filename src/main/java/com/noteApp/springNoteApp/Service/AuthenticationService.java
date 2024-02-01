package com.noteApp.springNoteApp.Service;

import com.noteApp.springNoteApp.DTO.JwtAuthenticationResponse;
import com.noteApp.springNoteApp.DTO.LoginRequest;
import com.noteApp.springNoteApp.DTO.RefreshTokenRequest;
import com.noteApp.springNoteApp.DTO.RegisterRequest;
import com.noteApp.springNoteApp.Entity.User;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    User signup(RegisterRequest registerRequest);
    JwtAuthenticationResponse signingIn(LoginRequest loginRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
