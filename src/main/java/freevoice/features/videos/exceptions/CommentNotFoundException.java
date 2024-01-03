package freevoice.features.videos.exceptions;

import java.io.Serial;

public class CommentNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;

    public CommentNotFoundException(String id) {
        super("!!!EXCEPTION!!! : the comment with id " + id + " was not found.");
    }
}