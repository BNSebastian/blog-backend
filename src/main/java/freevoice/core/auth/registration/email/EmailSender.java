package freevoice.core.auth.registration.email;

public interface EmailSender {
    void send(String to, String email);
}