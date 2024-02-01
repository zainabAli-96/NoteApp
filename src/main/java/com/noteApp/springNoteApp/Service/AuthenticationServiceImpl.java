package com.noteApp.springNoteApp.Service;

import com.noteApp.springNoteApp.DTO.JwtAuthenticationResponse;
import com.noteApp.springNoteApp.DTO.LoginRequest;
import com.noteApp.springNoteApp.DTO.RefreshTokenRequest;
import com.noteApp.springNoteApp.DTO.RegisterRequest;
import com.noteApp.springNoteApp.Entity.Role;
import com.noteApp.springNoteApp.Entity.User;
import com.noteApp.springNoteApp.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor

public class AuthenticationServiceImpl implements AuthenticationService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;


    public User signup(RegisterRequest registerRequest){
        User user = new User();
        user.setMobileNumber(registerRequest.getMobileNumber());
        user.setPassword(registerRequest.getPassword());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signingIn(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getMobileNumber(), loginRequest.getPassword())
        );
        var user = userRepository.findByMobileNumber(loginRequest.getMobileNumber())
                .orElseThrow(()-> new IllegalArgumentException("Invalid mobile number or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken= jwtService.generateRefreshToken(new HashMap<>(),user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;

    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userMobileNumber = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByMobileNumber(userMobileNumber).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;


        }
        return null ;
    }


}
