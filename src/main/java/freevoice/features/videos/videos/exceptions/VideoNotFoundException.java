package freevoice.features.videos.videos.exceptions;

import java.io.Serial;

public class VideoNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;

    public VideoNotFoundException(String name) {
        super("!!!EXCEPTION!!! : the video named " + name + " was not found.");
    }
}