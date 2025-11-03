package com.platzi.bdiaz.JanniePlay.web.exception;

import com.platzi.bdiaz.JanniePlay.domain.exception.MovieNotFoundException;
import com.platzi.bdiaz.JanniePlay.domain.exception.TitleMovieAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TitleMovieAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(TitleMovieAlreadyExistsException ex){
        ErrorResponseDTO error = new ErrorResponseDTO("movie-already-exists", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponseDTO>> handleException400(MethodArgumentNotValidException ex){
        List<ErrorResponseDTO> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> {
            errors.add(new ErrorResponseDTO(e.getField(), e.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleException404(MovieNotFoundException ex){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleExceptionGeneral(Exception ex){
        ErrorResponseDTO error = new ErrorResponseDTO("unknow-error", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
