package com.platzi.bdiaz.JanniePlay.domain.exception;

public class TitleMovieAlreadyExistsException extends RuntimeException {
    public TitleMovieAlreadyExistsException(String movieTitle) {
        super(String.format("La película %s, ya existe.", movieTitle));
    }
}
