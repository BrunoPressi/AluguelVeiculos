package com.bruno.pressi.aluguelveiculos.exceptions;

public class DuplicateClienteException extends RuntimeException {
    public DuplicateClienteException(String message) {
        super(message);
    }
}
