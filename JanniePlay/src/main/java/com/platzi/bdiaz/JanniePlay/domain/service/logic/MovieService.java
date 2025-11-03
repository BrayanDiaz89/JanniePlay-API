package com.platzi.bdiaz.JanniePlay.domain.service.logic;

import com.platzi.bdiaz.JanniePlay.domain.dto.MovieRequestDTO;
import com.platzi.bdiaz.JanniePlay.domain.dto.MovieResponseDTO;
import com.platzi.bdiaz.JanniePlay.domain.dto.UpdateMovieDTO;
import com.platzi.bdiaz.JanniePlay.domain.exception.MovieNotFoundException;
import com.platzi.bdiaz.JanniePlay.domain.repository.MovieRepository;
import com.platzi.bdiaz.JanniePlay.domain.service.logic.rules.ValidationCrudMovie;
import com.platzi.bdiaz.JanniePlay.persistence.entitie.Movie;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //Optimizamos las consultas que son de solo lectura, y no escritura en la db
public class MovieService {

    private final MovieRepository movieRepository;
    private final List<ValidationCrudMovie> movieValidationRules;

    public MovieService(MovieRepository movieRepository, List<ValidationCrudMovie> movieValidationRules) {
        this.movieRepository = movieRepository;
        this.movieValidationRules = movieValidationRules;
    }

    @Tool("Busca todas las películas que existan dentro de la plataforma")
    public List<MovieResponseDTO> getAllMovies() {
        return this.movieRepository.getAll();
    }

    public MovieResponseDTO getMovieById(Long id) {
        MovieResponseDTO movie = this.movieRepository.findById(id);
        if(movie == null){
            throw new MovieNotFoundException(id);
        }
        return movie;
    }

    @Transactional
    public MovieResponseDTO addMovie(MovieRequestDTO requestDTO){
        movieValidationRules.forEach(v-> v.validate(requestDTO));
        return this.movieRepository.save(requestDTO);
    }

    @Transactional
    public MovieResponseDTO updateMovie(Long id, UpdateMovieDTO updateMovieDTO){
        return this.movieRepository.updateMovie(id, updateMovieDTO);
    }

    @Transactional
    public void deleteMovie(Long id){
        this.movieRepository.deleteMovie(id);
    }

    @Transactional
    public void deactivateMovie(Long id){
        this.movieRepository.deactivateMovieStatus(id);
    }

    @Transactional
    public MovieResponseDTO activateMovie(Long id){
        return this.movieRepository.activateMovieStatus(id);
    }

}
