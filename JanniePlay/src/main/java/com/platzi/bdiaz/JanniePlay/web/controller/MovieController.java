package com.platzi.bdiaz.JanniePlay.web.controller;

import com.platzi.bdiaz.JanniePlay.domain.dto.MovieRequestDTO;
import com.platzi.bdiaz.JanniePlay.domain.dto.MovieResponseDTO;
import com.platzi.bdiaz.JanniePlay.domain.dto.SuggestRequestDTO;
import com.platzi.bdiaz.JanniePlay.domain.dto.UpdateMovieDTO;
import com.platzi.bdiaz.JanniePlay.domain.service.ai.JanniePlayAIService;
import com.platzi.bdiaz.JanniePlay.domain.service.logic.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movies", description = "Operations about movies of JanniePlay")
public class MovieController {

    private final MovieService movieService;
    private final JanniePlayAIService aiService;

    public MovieController(MovieService movieService, JanniePlayAIService aiService) {
        this.movieService = movieService;
        this.aiService = aiService;
    }

    @GetMapping("/home")
    public ResponseEntity<String> greetingCommunity(@Value("${spring.application.name}") String namePlataform){
        return ResponseEntity.ok(aiService.generateGreeting(namePlataform));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {
        return ResponseEntity.ok(this.movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Ger a movie by id",
            description = "Returns a movie that matches the identifier sent.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found movie"),
                    @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content)
            }
    )
    public ResponseEntity<MovieResponseDTO> getMovieById(@Parameter(
                                                                description = "identificador de la película a recuperar.",
                                                                example = "9")
                                                         @PathVariable Long id) {
        var movieDto = this.movieService.getMovieById(id);
        return ResponseEntity.ok(movieDto);
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> addMovie(@RequestBody @Valid MovieRequestDTO movieRequestDTO,
                                                     UriComponentsBuilder uriComponentsBuilder){
        MovieResponseDTO movieCreated = movieService.addMovie(movieRequestDTO);
        URI uri = uriComponentsBuilder.path("/movies/{id}").buildAndExpand(movieCreated.id()).toUri();
        return ResponseEntity.created(uri).body(movieCreated);
    }

    @PostMapping("/suggest")
    public ResponseEntity<String> generateMoviesSuggestion(@RequestBody @Valid SuggestRequestDTO suggestRequestDTO){
        return ResponseEntity.ok(this.aiService.generateMoviesSuggestions(suggestRequestDTO.userPreferences()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable Long id, @RequestBody @Valid UpdateMovieDTO updateMovieDTO){
        return ResponseEntity.ok(this.movieService.updateMovie(id, updateMovieDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        this.movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/status")
    public ResponseEntity deactivateMovie(@PathVariable Long id){
        this.movieService.deactivateMovie(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<MovieResponseDTO> activateMovie(@PathVariable Long id){
        return ResponseEntity.ok(this.movieService.activateMovie(id));
    }

}
