package com.example.notesapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoteNotFoundException.class)
    public Map<String, Object> handleNoteNotFound(NoteNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 404);
        response.put("error", ex.getMessage());
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errors", errors);

        return response;
    }
}
