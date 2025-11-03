package com.platzi.bdiaz.JanniePlay.domain.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateMovieDTO(
        @NotBlank(message = "El título no puede estar vacío.")
        String title,
        @PastOrPresent(message = "La fecha no debe ser mayor a la actual")
        LocalDate releaseDate,
        @Min(value = 0, message = "El valor mínimo del rating debe ser 0")
        @Max(value = 5, message = "El valor máximo del rating debe ser 5")
        @NotNull(message = "El rating no debe estar vacío, ya que estás actualizando su valor.")
        Double rating
) {
}
