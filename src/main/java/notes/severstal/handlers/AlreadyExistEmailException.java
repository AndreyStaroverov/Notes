package notes.severstal.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistEmailException extends RuntimeException {

    public AlreadyExistEmailException(String message) {
        super(message);
    }
}