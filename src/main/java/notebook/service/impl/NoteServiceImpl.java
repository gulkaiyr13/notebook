package notebook.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import notebook.entity.NoteEntity;
import notebook.entity.UserEntity;
import notebook.exception.ResourceNotFoundException;
import notebook.mapper.NoteMapper;
import notebook.model.NoteDTO;
import notebook.repository.NoteRepository;
import notebook.repository.UserRepository;
import notebook.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NoteServiceImpl implements NoteService {
    NoteRepository noteRepository;
    UserRepository userRepository;
    NoteMapper noteMapper;
    CloudinaryServiceImpl cloudinaryService;

    @Override
    public NoteDTO addNote(NoteDTO noteDto) {
        UserEntity user = userRepository.findById(noteDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + noteDto.getUserId()));

        NoteEntity noteEntity = noteMapper.toEntity(noteDto);
        noteEntity.setUser(user);

        return noteMapper.toDto(noteRepository.save(noteEntity));
    }

    @Override
    public NoteDTO getNoteById(Long moodId) {
        NoteEntity mood = noteRepository.findById(moodId)
                .orElseThrow(() -> new ResourceNotFoundException("Mood not found with id: " + moodId));
        return noteMapper.toDto(mood);
    }


    @Override
    public NoteDTO updateNote(NoteDTO updatedNoteDto) {
        return noteMapper.toDto(noteRepository.save(noteMapper.toEntity(updatedNoteDto)));
    }

    @Override
    public void deleteNote(Long moodId) {
        noteRepository.deleteById(moodId);
    }

    @Override
    public NoteDTO toggleFavorite(Long noteId) {
        NoteEntity note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id: " + noteId));

        note.setFavorite(!note.isFavorite());
        return noteMapper.toDto(noteRepository.save(note));
    }

    @Override
    public String uploadImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty or null");
        }
        return cloudinaryService.uploadFile(file, "notes");
    }
}
