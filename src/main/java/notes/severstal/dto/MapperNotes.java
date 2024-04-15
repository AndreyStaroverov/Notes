package notes.severstal.dto;

import notes.severstal.model.Note;
import notes.severstal.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MapperNotes {

    public MapperNotes() {
    }

    public static Note dtoToNote(NoteDto noteDto, User owner) {
        return new Note(
                noteDto.getId(),
                noteDto.getText(),
                owner,
                noteDto.getPinned(),
                noteDto.getCreateDate()
        );
    }


    public static NoteDto noteToDto(Note save) {
        return new NoteDto(
                save.getId(),
                save.getText(),
                save.getOwner().getId(),
                save.getPinned(),
                save.getCreateDate()
        );
    }

    public static Collection<NoteDto> noteToDtoCollection(List<Note> notes) {
        Collection<NoteDto> noteDtos = new ArrayList<>();
        for (Note note: notes) {
            noteDtos.add(new NoteDto(
                    note.getId(),
                    note.getText(),
                    note.getOwner().getId(),
                    note.getPinned(),
                    note.getCreateDate()
            ));
        }
        return noteDtos;
    }
}
