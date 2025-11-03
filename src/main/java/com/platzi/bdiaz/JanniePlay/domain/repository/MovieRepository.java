package com.platzi.bdiaz.JanniePlay.domain.repository;

import com.platzi.bdiaz.JanniePlay.domain.dto.MovieRequestDTO;
import com.platzi.bdiaz.JanniePlay.domain.dto.MovieResponseDTO;
import com.platzi.bdiaz.JanniePlay.domain.dto.UpdateMovieDTO;

import java.util.List;


public interface MovieRepository {

    List<MovieResponseDTO> getAll();
    MovieResponseDTO findById(Long id);
    MovieResponseDTO save(MovieRequestDTO requestDTO);
    Boolean existsByTitle(String title);
    MovieResponseDTO updateMovie(Long id, UpdateMovieDTO updateMovieDTO);
    void deleteMovie(Long id);
    void deactivateMovieStatus(Long id);
    MovieResponseDTO activateMovieStatus(Long id);

}
