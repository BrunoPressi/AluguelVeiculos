package com.bruno.pressi.aluguelveiculos.web.exception;

import com.bruno.pressi.aluguelveiculos.exceptions.EntityNotFoundException;
import com.bruno.pressi.aluguelveiculos.exceptions.DuplicateEntityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<ErrorMessage> duplicateClienteException(HttpServletRequest request, RuntimeException e) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setPath(request.getRequestURI());
        errorMessage.setStatusCode(HttpStatus.CONFLICT.value());
        errorMessage.setStatusMessage(HttpStatus.CONFLICT.getReasonPhrase());
        errorMessage.setErrorMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(HttpServletRequest request, BindingResult bindingResult) {

        ErrorMessage errorMessage = new ErrorMessage(
                LocalDateTime.now(),
                request.getRequestURI(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(),
                "Campos Inválidos",
                bindingResult
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessage);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> clienteNotFoundException(HttpServletRequest request, RuntimeException e) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setPath(request.getRequestURI());
        errorMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorMessage.setStatusMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorMessage.setErrorMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

}
