package com.platzi.bdiaz.JanniePlay.domain.dto;

import com.platzi.bdiaz.JanniePlay.domain.enums.Genre;

import java.time.LocalDate;

public record MovieResponseDTO(
        Long id,
        String title,
        Integer duration,
        Genre genre,
        LocalDate releaseDate,
        Double rating,
        Boolean isAvailable
) {
}
