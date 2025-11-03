package com.platzi.bdiaz.JanniePlay.persistence;

import com.platzi.bdiaz.JanniePlay.domain.dto.MovieRequestDTO;
import com.platzi.bdiaz.JanniePlay.domain.dto.MovieResponseDTO;
import com.platzi.bdiaz.JanniePlay.domain.dto.UpdateMovieDTO;
import com.platzi.bdiaz.JanniePlay.domain.repository.MovieRepository;
import com.platzi.bdiaz.JanniePlay.persistence.crud.CrudMovieEntity;
import com.platzi.bdiaz.JanniePlay.persistence.entitie.Movie;
import com.platzi.bdiaz.JanniePlay.persistence.mapper.MovieMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieEntityRepository implements MovieRepository {

    private final MovieMapper movieMapper;
    private final CrudMovieEntity crudMovieEntity;

    public MovieEntityRepository(MovieMapper movieMapper, CrudMovieEntity crudMovieEntity) {
        this.movieMapper = movieMapper;
        this.crudMovieEntity = crudMovieEntity;
    }

    @Override
    public List<MovieResponseDTO> getAll() {
        return this.movieMapper.toDto(this.crudMovieEntity.findAll());
    }

    @Override
    public MovieResponseDTO findById(Long id) {
         return this.crudMovieEntity.findById(id)
                 .map(movieMapper::toDto)
                 .orElse(null);
    }

    @Override
    public MovieResponseDTO save(MovieRequestDTO requestDTO) {
        Movie movie = movieMapper.toEntity(requestDTO);
        return this.movieMapper.toDto(crudMovieEntity.save(movie));
    }

    @Override
    public Boolean existsByTitle(String title) {
        return this.crudMovieEntity.existsByTitulo(title);
    }

    @Override
    public MovieResponseDTO updateMovie(Long id, UpdateMovieDTO updateMovieDTO) {
        Movie movie = this.crudMovieEntity.findById(id).orElse(null);
        if(movie == null) return null;
        //Opción 1) movie.updateMovie(updateMovieDTO); mala práctica
        //Opción 2, mejor práctica
        this.movieMapper.updateMovie(updateMovieDTO, movie);
        return this.movieMapper.toDto(crudMovieEntity.save(movie));
    }

    @Override
    public void deleteMovie(Long id) {
        try{
            this.crudMovieEntity.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Error al eliminar la película");
        }
    }

    @Override
    public void deactivateMovieStatus(Long id){
        Movie movie = crudMovieEntity.findById(id).orElse(null);
        if(movie != null && !"N".equalsIgnoreCase(movie.getContentStatus())){
            this.crudMovieEntity.deactivateStatusMovie(id);
        }
    }

    @Override
    public MovieResponseDTO activateMovieStatus(Long id) {
        Movie movie = crudMovieEntity.findById(id).orElse(null);
        if(movie == null) return null;
        if(!"D".equalsIgnoreCase(movie.getContentStatus())){
            movie.setContentStatus("D");
        }
        return this.movieMapper.toDto(movie);
    }
}
