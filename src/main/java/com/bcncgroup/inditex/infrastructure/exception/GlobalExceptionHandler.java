package com.bcncgroup.inditex.infrastructure.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePriceNotFoundException(PriceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Maneja excepciones de validación: 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        ErrorResponse error = new ErrorResponse("Validación fallida", HttpStatus.BAD_REQUEST.value(), errors);
        logger.error("Error 400 : {}",ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Maneja excepciones genéricas: 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR.value());
        logger.error("Error 500: {}",ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFormatException(InvalidFormatException ex) {
        return Stream.ofNullable(ex.getPath())
            .flatMap(List::stream)
            .findFirst()
            .map(ref -> buildErrorMessage(ex.getValue(), ref.getFieldName()))
            .map(this::createErrorResponse)
            .orElseGet(() -> createDefaultError(ex));
    }
    
    private String buildErrorMessage(Object invalidValue, String field) {
        return String.format("El valor '%s' no es válido para el campo '%s'. Usa el formato yyyy-MM-dd'T'HH:mm:ss.",
            invalidValue, field);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(String message) {
        logger.error("Error de parseo: {}", message);
        return new ResponseEntity<>(new ErrorResponse(message, 400), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> createDefaultError(InvalidFormatException ex) {
        logger.error("Error desconocido en campo: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse("Error de formato", 400), HttpStatus.BAD_REQUEST);
    }

    
}


