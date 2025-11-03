package com.platzi.bdiaz.JanniePlay.domain.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(Long idMovie) {
        super(String.format("Película no encontrada en la base de datos, valida el id= %d.", idMovie));
    }
}
