package notes.severstal.service;

import lombok.extern.slf4j.Slf4j;
import notes.severstal.dto.NoteDto;
import notes.severstal.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(@Autowired NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Collection<NoteDto> getNotesByUserId(Long userId, Long from, Long size) {
        return null;
    }

    public NoteDto createNote(NoteDto noteDto) {
        return null;
    }

    public void deleteNoteById(Long noteId) {
    }

    public NoteDto getNoteById(Long noteId) {
        return null;
    }

    public NoteDto updateNote(Long noteId) {
        return null;
    }
}
