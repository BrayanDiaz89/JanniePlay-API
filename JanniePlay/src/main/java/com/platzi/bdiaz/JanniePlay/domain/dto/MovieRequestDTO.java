package com.platzi.bdiaz.JanniePlay.domain.dto;

import com.platzi.bdiaz.JanniePlay.domain.enums.Genre;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record MovieRequestDTO(
        @NotBlank(message = "El título no debe estar vacío.")
        String title,
        @NotNull(message = "Debe ingresar una duración para la película.")
        @Min(value = 60, message = "El valor mínimo de la duración es de 60min")
        @Max(value = 240, message = "El valor máximo de duración para una película es de 240min")
        Integer duration,
        @NotNull(message = "El género de la película no debe estar vacío.")
        Genre genre,
        @Min(value = 0, message = "El valor mínimo del rating debe ser 0")
        @Max(value = 5, message = "El valor máximo del rating debe ser 5")
        Double rating,
        @PastOrPresent(message = "La fecha no debe ser mayor a la actual.")
        LocalDate releaseDate,
        Boolean isAvailable
) {
}
