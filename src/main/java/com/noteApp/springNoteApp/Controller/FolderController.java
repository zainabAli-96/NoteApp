package com.noteApp.springNoteApp.Controller;

import com.noteApp.springNoteApp.DTO.FolderRequest;
import com.noteApp.springNoteApp.DTO.FolderResponse;
import com.noteApp.springNoteApp.Entity.Folder;
import com.noteApp.springNoteApp.Service.AuthenticationService;
import com.noteApp.springNoteApp.Service.FolderService;
import com.noteApp.springNoteApp.Service.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/folders")
@RequiredArgsConstructor
public class FolderController {

    private final AuthenticationService authenticationService;
    private final JWTService jwtService;
    private final FolderService folderService;


// Return the user folders list
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Folder>> getFoldersForUser(@PathVariable Long userId, HttpServletRequest request) {
        // Get the user ID from the authentication token
        Long authenticatedUserId = jwtService.getUserIdFromToken(request);

        // Check if the authenticated user is authorized to access the folders
        if (!authenticatedUserId.equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Get the folders by user ID
        List<Folder> folders = folderService.getFoldersByUserId(userId);

        //Check if user has folder(s)

        if(folders.isEmpty()){

            return ResponseEntity.noContent().build();
        }


        return ResponseEntity.ok(folders);
    }

    // Create a folder
   @PostMapping("/user/{userId}")
  public ResponseEntity<FolderResponse> createFolder(@PathVariable("userId") Long userId,
                                             @RequestBody FolderRequest folderRequest) {

           return ResponseEntity.ok(folderService.createFolder(userId,folderRequest));
   }

   //Return a Specific folder
   @GetMapping("/{folderId}")
   public ResponseEntity<FolderResponse> getFolderById(@PathVariable("folderId") Long folderId) {

       return ResponseEntity.ok(folderService.getFolderById(folderId));
   }





}
