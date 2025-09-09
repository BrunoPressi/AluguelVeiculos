package com.bruno.pressi.aluguelveiculos.web.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorMessage {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime timestamp;
    private String path;
    private int statusCode;
    private String statusMessage;
    private String errorMessage;

    private Map<String, String> fieldErrors = new HashMap<>();

    public ErrorMessage(LocalDateTime timestamp, String errorMessage, String statusMessage, int statusCode, String path) {
        this.timestamp = timestamp;
        this.errorMessage = errorMessage;
        this.statusMessage = statusMessage;
        this.statusCode = statusCode;
        this.path = path;
    }

    public ErrorMessage(LocalDateTime timestamp, String statusMessage, int statusCode, String path, String errorMessage, BindingResult bindingResult) {
        this.timestamp = timestamp;
        this.statusMessage = statusMessage;
        this.statusCode = statusCode;
        this.path = path;
        this.errorMessage = errorMessage;
        addErrors(bindingResult);
    }

    private final void addErrors(BindingResult bindingResult) {
        List<FieldError> allErrors = bindingResult.getFieldErrors();

        for (FieldError e : allErrors) {
            fieldErrors.put(e.getField(), e.getDefaultMessage());
        }
    }
}
