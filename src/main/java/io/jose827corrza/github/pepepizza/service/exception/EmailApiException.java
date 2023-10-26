package io.jose827corrza.github.pepepizza.service.exception;

public class EmailApiException extends RuntimeException{
    public EmailApiException() {
        super("Error sending message to registered Emails, USED FOR TESTING THE TRANSACTIONAL ANNOTATION");
    }
}
