package notes.severstal.controllers;

import notes.severstal.dto.NoteDto;
import notes.severstal.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
    public Collection<NoteDto> getNotes(@PathVariable @Valid @Positive Long userId,
                                        @RequestParam(value = "from", defaultValue = "0") @Valid @Min(0) Long from,
                                        @RequestParam(value = "size", defaultValue = "10") @Valid @Min(1) Long size) {
        return noteService.getNotesByUserId(userId, from, size);
    }

    @GetMapping("/{noteId}")
    @ResponseStatus(HttpStatus.OK)
    public NoteDto getNote(@PathVariable @Valid @Positive Long noteId) {
        return noteService.getNoteById(noteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDto postNote(@Valid @RequestBody NoteDto noteDto) {
        return noteService.createNote(noteDto);
    }

    @DeleteMapping("/{noteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable @Valid @Positive Long noteId) {
        noteService.deleteNoteById(noteId);
    }

    @PatchMapping("/{noteId}")
    @ResponseStatus(HttpStatus.OK)
    public NoteDto patchNote(@PathVariable @Valid @Positive Long noteId) {
        return noteService.updateNote(noteId);
    }

}
