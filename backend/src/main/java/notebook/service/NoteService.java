package notebook.service;

import notebook.model.NoteDTO;
import org.springframework.web.multipart.MultipartFile;

public interface NoteService {
    NoteDTO addNote(NoteDTO noteDto);
    NoteDTO getNoteById(Long moodId);
    NoteDTO updateNote(NoteDTO updatedNoteDto);
    NoteDTO toggleFavorite(Long noteId);
    String uploadImage(MultipartFile file);
    void deleteNote(Long moodId);
}
