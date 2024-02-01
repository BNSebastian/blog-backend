package freevoice.shared.templates.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericNotFoundEx extends RuntimeException {
    public GenericNotFoundEx() {
        super();
        log.error("entry not found");
    }
    public GenericNotFoundEx(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }
    public GenericNotFoundEx(String message) {
        super(message);
        log.error(message);
    }
    public GenericNotFoundEx(Throwable cause) {
        super(cause);
        log.error("entry not found ", cause);

    }
}
