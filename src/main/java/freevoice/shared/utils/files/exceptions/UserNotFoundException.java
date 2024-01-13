package freevoice.shared.utils.files.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;

    public UserNotFoundException(String name) {
        super("!!!EXCEPTION!!! : the video named " + name + " was not found.");
    }
}