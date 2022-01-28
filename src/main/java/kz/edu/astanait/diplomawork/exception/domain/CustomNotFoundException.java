package kz.edu.astanait.diplomawork.exception.domain;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message) {
        super(message);
    }
}