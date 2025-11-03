package com.platzi.bdiaz.JanniePlay.persistence.mapper;

import com.platzi.bdiaz.JanniePlay.domain.dto.MovieRequestDTO;
import com.platzi.bdiaz.JanniePlay.domain.dto.MovieResponseDTO;
import com.platzi.bdiaz.JanniePlay.domain.dto.UpdateMovieDTO;
import com.platzi.bdiaz.JanniePlay.persistence.entitie.Movie;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenreMapper.class, ContentStatusMapper.class})
public interface MovieMapper {

    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "duracion", target = "duration")
    @Mapping(source = "genero", target = "genre", qualifiedByName = "stringToGenre") //Con qualifiedByName llamamos al metodo que convierte el String a enum
    @Mapping(source = "fechaEstreno", target = "releaseDate")
    @Mapping(source = "calificacion", target = "rating")
    @Mapping(source = "contentStatus", target = "isAvailable", qualifiedByName = "stringToBoolean")
    MovieResponseDTO toDto(Movie movie);
    List<MovieResponseDTO> toDto(List<Movie> movies);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "title", target = "titulo")
    @Mapping(source = "duration", target = "duracion")
    @Mapping(source = "genre", target = "genero", qualifiedByName = "genreToString")
    @Mapping(source = "rating", target = "calificacion")
    @Mapping(source = "releaseDate", target = "fechaEstreno")
    @Mapping(source = "isAvailable", target = "contentStatus", qualifiedByName = "booleanToString", defaultValue = "true")
    Movie toEntity(MovieRequestDTO requestDTO);

    //@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) //Lo que viene null no se toca
    @Mapping(target = "titulo", source = "title")
    @Mapping(target = "fechaEstreno", source = "releaseDate")
    @Mapping(target = "calificacion", source = "rating")
    void updateMovie(UpdateMovieDTO updateMovieDTO, @MappingTarget Movie movie);

}
