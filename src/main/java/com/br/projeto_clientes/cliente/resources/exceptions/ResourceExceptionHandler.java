package com.br.projeto_clientes.cliente.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

//Pode Capturar Excecções
@ControllerAdvice
public class ResourceExceptionHandler {

    // Defini o tipo de Exceção
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandartError> entityNotFoundException(EntityNotFoundException e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError error = new StandartError();

        error.setStatus(status.value());
        error.setError("Resource not found");
        error.setMessage(e.getMessage());
        error.setTimeStamp(Instant.now());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrors> validationErrorsException(MethodArgumentNotValidException e,
            HttpServletRequest request) {

        ValidationErrors error = new ValidationErrors();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        error.setStatus(status.value());
        error.setError("Validation Errors");
        error.setMessage(e.getMessage());
        error.setTimeStamp(Instant.now());
        error.setPath(request.getRequestURI());

        e.getBindingResult()
                .getFieldErrors()
                .forEach(err -> error.addError(err.getDefaultMessage()));

        return ResponseEntity.status(status).body(error);
    }

}
