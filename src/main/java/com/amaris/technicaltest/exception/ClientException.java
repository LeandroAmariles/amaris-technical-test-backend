package com.amaris.technicaltest.exception;

public class ClientException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClientException(String message) {
        super(message);
    }
}
