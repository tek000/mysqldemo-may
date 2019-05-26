package pl.dominisz.mysqldemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

//@ResponseStatus(HttpStatus.NOT_FOUND)
//public class UserNotFoundException extends RuntimeException {
//
//    public UserNotFoundException(Integer userId) {
//        super("User with id " + userId + " not found");
//    }
//}


@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer userId) {
        super("User with id " + userId + " not found");
    }
}