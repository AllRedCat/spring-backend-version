package com.vagaslivre.backend.Exception;

public class ErrorMessageException extends RuntimeException {
    public ErrorMessageException(String message) {
        super(message);
    }
}
