package pl.dominisz.mysqldemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidPasswordException extends EntityNotFoundException {

    public InvalidPasswordException() {
        super("Invalid found");
    }
}
