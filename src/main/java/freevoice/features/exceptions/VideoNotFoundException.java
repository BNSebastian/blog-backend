package freevoice.features.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "A video with this name was not found")
public class VideoNotFoundException extends RuntimeException {

}