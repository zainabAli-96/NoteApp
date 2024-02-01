package com.noteApp.springNoteApp.Service;

import com.noteApp.springNoteApp.DTO.CreateNoteRequest;
import com.noteApp.springNoteApp.DTO.FolderRequest;
import com.noteApp.springNoteApp.DTO.FolderResponse;
import com.noteApp.springNoteApp.Entity.Folder;
import com.noteApp.springNoteApp.Entity.Note;
import com.noteApp.springNoteApp.Entity.User;
import com.noteApp.springNoteApp.Repository.FolderRepository;
import com.noteApp.springNoteApp.Repository.ImageRepository;
import com.noteApp.springNoteApp.Repository.NoteRepository;
import com.noteApp.springNoteApp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService{

    private final FolderRepository folderRepository;
    private final UserRepository userRepository;

    private final NoteRepository noteRepository;
    private final ImageRepository imageRepository;

    @Override
    public FolderResponse createFolder(Long userId, FolderRequest folderRequest) {
        Optional<User> user = userRepository.findById(userId);


        if (user.isPresent()) {
            User user_ = user.get();
            Folder folder = new Folder();

            folder.setUser(user_);
            folder.setFolderName(folderRequest.getFolderName());
            folder.setPublic(folderRequest.isPublic());


            Folder savedFolder = folderRepository.save(folder);

            // Create a FolderResponse object based on the saved folder
            FolderResponse folderResponse = new FolderResponse(
                    savedFolder.getId(),
                    savedFolder.getFolderName(),
                    savedFolder.isPublic()
            );

            return folderResponse;
          //  return folderRepository.save(folder);
        } else {
            throw new IllegalStateException("User not found");
        }
    }


    public FolderResponse getFolderById(Long folderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new NoSuchElementException("Folder not found"));
        return new FolderResponse(folder.getId(), folder.getFolderName(), folder.isPublic());

    }

    public Folder getFolderByIdForNotes(Long folderId){
    return null;

    }


    public List<Folder> getFoldersByUserId(Long userId) {
        return folderRepository.findByUserId(userId);
    }

    //Note
  /*  public Note createNoteInFolder(Folder folder, CreateNoteRequest noteCreateRequest) {

        // Create a new note object
        Note note = new Note();
        note.setTitle(noteCreateRequest.getTitle());
        note.setContent(noteCreateRequest.getContent());
        note.setFolder(folder);

        // Save the note in the repository or perform any other necessary operations
        Note createdNote = noteRepository.save(note);

        return createdNote;
    }

    //Image

     public Image uploadImageToFolder(Folder folder, MultipartFile imageFile) {
        // Create a new image object
        Image image = new Image();
        image.setFileName(imageFile.getOriginalFilename());
        image.setFolder(folder);

        // Save the image file or perform any other necessary operations
        String filePath = saveImageFile(imageFile);
        image.setFilePath(filePath);

        // Save the image in the repository
        Image uploadedImage = imageRepository.save(image);

        return uploadedImage;
    }
     */
}
