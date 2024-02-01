package com.noteApp.springNoteApp.Controller;

import com.noteApp.springNoteApp.DTO.JwtAuthenticationResponse;
import com.noteApp.springNoteApp.DTO.LoginRequest;
import com.noteApp.springNoteApp.DTO.RefreshTokenRequest;
import com.noteApp.springNoteApp.DTO.RegisterRequest;
import com.noteApp.springNoteApp.Entity.User;
import com.noteApp.springNoteApp.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<User> signup(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authenticationService.signup(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signingIn(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authenticationService.signingIn(loginRequest));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
