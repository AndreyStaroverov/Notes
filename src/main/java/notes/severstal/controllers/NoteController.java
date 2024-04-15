package notes.severstal.controllers;

import notes.severstal.dto.NoteDto;
import notes.severstal.dto.NoteDtoUpdate;
import notes.severstal.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Collection;

@RestController
@RequestMapping(path = "/notes")
@Validated
public class NoteController {

    private final NoteService noteService;


    public NoteController(@Autowired NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<NoteDto> getNotes(@PathVariable @Valid @Positive Long userId) {
        return noteService.getNotesByUserId(userId);
    }

    @GetMapping("/{noteId}")
    @ResponseStatus(HttpStatus.OK)
    public NoteDto getNote(@PathVariable @Valid @Positive Long noteId) {
        return noteService.getNoteById(noteId);
    }

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDto postNote(@Valid @RequestBody NoteDto noteDto) {
        return noteService.createNote(noteDto);
    }

    @CrossOrigin
    @DeleteMapping("/{noteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable @Valid @Positive Long noteId) {
        noteService.deleteNoteById(noteId);
    }

    @CrossOrigin
    @PatchMapping("/user/{userId}/note/{noteId}")
    @ResponseStatus(HttpStatus.OK)
    public NoteDto patchNote(@PathVariable @Valid @Positive Long noteId,
                             @PathVariable @Positive Long userId,
                             @Valid @RequestBody NoteDtoUpdate noteDto) {
        return noteService.updateNote(noteId, userId, noteDto);
    }

}
