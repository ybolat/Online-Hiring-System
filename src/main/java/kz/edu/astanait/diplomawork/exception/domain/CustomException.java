package kz.edu.astanait.diplomawork.exception.domain;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}