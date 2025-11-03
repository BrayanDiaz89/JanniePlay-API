package com.platzi.bdiaz.JanniePlay.domain.service.logic.rules.create;

import com.platzi.bdiaz.JanniePlay.domain.dto.MovieRequestDTO;
import com.platzi.bdiaz.JanniePlay.domain.repository.MovieRepository;
import com.platzi.bdiaz.JanniePlay.domain.service.logic.rules.ValidationCrudMovie;
import com.platzi.bdiaz.JanniePlay.domain.exception.TitleMovieAlreadyExistsException;
import org.springframework.stereotype.Component;

@Component
public class ValidationTitleExists implements ValidationCrudMovie {

    private final MovieRepository movieRepository;

    public ValidationTitleExists(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void validate(MovieRequestDTO movieRequestDTO) {
        if(movieRepository.existsByTitle(movieRequestDTO.title())){
            throw new TitleMovieAlreadyExistsException(movieRequestDTO.title());
        }
    }
}
