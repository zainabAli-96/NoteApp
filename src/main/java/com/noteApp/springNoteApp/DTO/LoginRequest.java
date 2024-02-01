package com.noteApp.springNoteApp.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String mobileNumber;
    private String password;
}
