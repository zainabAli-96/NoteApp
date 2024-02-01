package com.noteApp.springNoteApp.DTO;


import lombok.Data;

@Data

public class RegisterRequest {
    private String mobileNumber;
    private String password;
}
