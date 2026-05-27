package com.spotter_proyect.spotter.core.exceptions;

import com.spotter_proyect.spotter.core.exceptions.DTO.ApiErrorResponse;
import com.spotter_proyect.spotter.core.exceptions.errors.DuplicateActionException;
import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.exceptions.errors.UnauthenticatedException;
import com.spotter_proyect.spotter.core.exceptions.errors.UnauthorizedActionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // CASO 1: Recursos no encontrados (Error 404)
    // Se activa si lanzas: throw new ResourceNotFoundException("Entrenador no encontrado");
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ApiErrorResponse error = new ApiErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // CASO 2: Acciones Duplicadas o Conflictos (Error 409)
    // Se activa si lanzas: throw new DuplicateActionException("Ya sigues a este entrenador");
    @ExceptionHandler(DuplicateActionException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicateAction(DuplicateActionException ex) {
        ApiErrorResponse error = new ApiErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // CASO 3: Errores de Validación de Spring (Error 400)
    // Se activa automáticamente si usas @Valid en tu DTO y el email está mal, o la password es corta.
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationErrors(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        // Extraemos el mensaje del primer error de validación
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        ApiErrorResponse error = new ApiErrorResponse("Error en los datos: " + errorMessage, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // CASO 4: Error de autorización
    @ExceptionHandler(UnauthorizedActionException.class)
    public ResponseEntity<ApiErrorResponse> handleUnauthorizedAction(UnauthorizedActionException ex) {
        ApiErrorResponse error = new ApiErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    // CASO 5: Error de autorizacion nula
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<ApiErrorResponse> handleUnauthenticatedException(UnauthenticatedException ex) {
        ApiErrorResponse error = new ApiErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    // CASO FINAL: El comodín final (Error 500)
    // Atrapa cualquier otro error inesperado (bases de datos caídas, NullPointers...)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericError(Exception ex) {
        // Aquí podrías imprimir ex.printStackTrace() en la consola para ti
        ApiErrorResponse error = new ApiErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}