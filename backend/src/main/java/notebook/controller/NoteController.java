package notebook.controller;

import lombok.RequiredArgsConstructor;
import notebook.model.NoteDTO;
import notebook.service.impl.NoteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteServiceImpl noteService;

    @PostMapping
    public ResponseEntity<NoteDTO> createNote(@RequestBody NoteDTO noteDTO) {

        return ResponseEntity.ok(noteService.addNote(noteDTO));
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<NoteDTO> updateNote(@RequestBody NoteDTO noteDto) {
        return ResponseEntity.ok(noteService.updateNote(noteDto));
    }

    @GetMapping("/note/{id}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }


    @PostMapping("/{id}")
    public ResponseEntity<NoteDTO> toggleNote(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.toggleFavorite(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String imageUrl = noteService.uploadImage(file);
        return ResponseEntity.ok(imageUrl);
    }

}
