package com.empresa.funcionarios.application.config;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ControllerAdvice {


    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public String handleDateValidationExceptions(InvalidFormatException ex) {
        //TODO: colocar para tratar no mesmo metodo para conseguir devolver todos as validacoes que falharam
        ex.printStackTrace();
        return String.format("data_nascimento: Valor informado nao é valido. Informado: %s. formato aceito dd/mm/aaaa", ex.getValue());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        int statusCode = ex.getStatus().value();
        ErrorResponse errorResponse = new ErrorResponse(statusCode, ex.getReason());

        return ResponseEntity.status(statusCode).body(errorResponse);
    }

}
