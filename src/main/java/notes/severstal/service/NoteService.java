package notes.severstal.service;

import notes.severstal.dto.MapperNotes;
import notes.severstal.dto.NoteDto;
import notes.severstal.dto.NoteDtoUpdate;
import notes.severstal.handlers.AlreadyExistEmailException;
import notes.severstal.handlers.NotFoundException;
import notes.severstal.repository.NoteRepository;
import notes.severstal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(@Autowired NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Collection<NoteDto> getNotesByUserId(Long userId) {
        return MapperNotes.noteToDtoCollection(noteRepository.findAllByOwner_id(userId));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public NoteDto createNote(NoteDto noteDto) {
        try {
            return MapperNotes.noteToDto(
                    noteRepository.save(MapperNotes.dtoToNote(noteDto,
                            userRepository.getById(noteDto.getOwner())))
            );
        } catch (Exception e) {
            throw new AlreadyExistEmailException("Some exception");
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteNoteById(Long noteId) {
        if (!noteRepository.existsById(noteId)) {
            throw new NotFoundException(String.format("Note with id=%s does not exist", noteId));
        }
        noteRepository.deleteById(noteId);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public NoteDto getNoteById(Long noteId) {
        return MapperNotes.noteToDto(noteRepository.getReferenceById(noteId));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public NoteDto updateNote(Long noteId, Long userId, NoteDtoUpdate noteDto) {
        if (!noteRepository.existsById(noteId)) {
            throw new NotFoundException(String.format("Note with id=%s does not exist", noteId));
        }
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(String.format("User with id=%s does not exist", userId));
        }

        var note = noteRepository.getReferenceById(noteId);

        if (!Objects.equals(note.getOwner().getId(), userId)) {
            throw new NotFoundException(String.format("This note author is not id=%s", userId));
        }

        note.setText(noteDto.getText());
        note.setPinned(noteDto.getPinned());
        note.setImageLink(noteDto.getImageLink());

        return MapperNotes.noteToDto(noteRepository.save(note));
    }
}
